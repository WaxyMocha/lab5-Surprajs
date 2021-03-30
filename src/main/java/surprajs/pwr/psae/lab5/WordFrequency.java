package surprajs.pwr.psae.lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class WordFrequency {
    private HashMap<String, int[]> wordCount;
    private final static String CSV_SEP = ",";
    
    public WordFrequency() {
        this.wordCount = new HashMap<>();
        
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String directory = "1000_novels";
        File dir = new File(String.format("%s%s",absolutePath,directory));
        
        File[] filesInDirectory = dir.listFiles((dirr,name) -> name.toLowerCase().endsWith(".txt"));
        
        int directorySize = filesInDirectory.length;
        int counter = 1;
        
        for (File filepath: filesInDirectory) {
            if (filepath.getName().substring(filepath.getName().lastIndexOf(".")).equals(".txt")) {
                BufferedReader file = null;
                try {
                    file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), Charset.forName("UTF-8").newDecoder()));
                    String read;
                    while ((read = file.readLine()) != null) {
                        if (!read.isEmpty()) {
                            String line = new String(read.getBytes());
                            String[] wordsInLine = line.replaceAll("[^a-zA-ZÀ-ž ]", "").toLowerCase().trim().split("\\s+");
                            for (String word: wordsInLine) {
                                if (!word.isEmpty()) {
                                    if (!this.wordCount.containsKey(word)) {
                                        this.wordCount.put(word,new int[directorySize+1]);
                                        this.wordCount.get(word)[0] = 1;
                                        this.wordCount.get(word)[counter] = 1;
                                    } else {
                                        ++this.wordCount.get(word)[0];
                                        ++this.wordCount.get(word)[counter];
                                    }
                                }
                            }
                        }
                    }
                    ++counter;
                } catch (IOException e) {
                    System.err.println(e);
                } finally {
                    try {
                        if (file != null) file.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                }
            }
        }
        writeToCSV(this.wordCount, filesInDirectory);
    }
    
    public static void writeToCSV(HashMap<String,int[]> words, File[] filenames) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String dir = "counted_words";
        new File(String.format("%s%s", absolutePath, dir)).mkdirs();
        OutputStreamWriter file = null;
        try {
            file = new OutputStreamWriter(new FileOutputStream(String.format("%s%s%s%sall_words.csv",absolutePath,sep,dir,sep)),Charset.forName("UTF-8").newEncoder());
            file.write(String.format("Word%1$sIn all files%1$s",CSV_SEP));
            String[] filenames_line = new String[filenames.length];
            for (int i = 0; i < filenames.length; ++i) {
                filenames_line[i] = filenames[i].getName().replace(".txt","");
            }                
            file.write(String.join(CSV_SEP,filenames_line));
            
            for (Map.Entry<String,int[]> word : words.entrySet()) {
                file.write("\n");
                file.write(String.format("%s%s", word.getKey(),CSV_SEP));
                String[] csv_line = Arrays.stream(word.getValue()).mapToObj(String::valueOf).toArray(String[]::new);
                file.write(String.join(CSV_SEP,csv_line).replaceAll("\\b0\\b",""));
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (file != null) file.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}