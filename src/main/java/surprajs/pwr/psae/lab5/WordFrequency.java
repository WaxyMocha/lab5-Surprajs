package surprajs.pwr.psae.lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Stream;

public class WordFrequency {
    public WordFrequency() {
        HashMap<String, Integer> wordCount = new HashMap<>();
        HashMap<String, Integer> wordCountAll = new HashMap<>();     
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String directory = "1000_novels_test";
        File dir = new File(String.format("%s1000_novels",absolutePath));
        File[] filesInDirectory = dir.listFiles();
        int counter = 0;
        for (File filepath: filesInDirectory) {
            try (BufferedReader file = new BufferedReader(
            new InputStreamReader(
            new FileInputStream(filepath),
            Charset.forName("UTF-8").newDecoder()));) {
            String read;
            wordCount.clear();
            while ((read = file.readLine()) != null) {
                if (!read.isEmpty()) {
                    String line = new String(read.getBytes());
                    String[] wordsInLine = line.replaceAll("[^a-zA-ZÀ-ž ]", "").toLowerCase().trim().split("\\s+");
                    for (String word: wordsInLine) {
                        if (!word.isEmpty()) {
                            wordCount.put(word, wordCount.getOrDefault(word,0)+1);
                            wordCountAll.put(word, wordCountAll.getOrDefault(word,0)+1);  
                        }
                    }
                }
            }
                    writeToCSV(wordCount, String.format("%s_test.csv", filepath.getName()));
                    //System.out.println(wordCount.get(""));
            ++counter;
            if (counter%100 == 0) System.out.println(counter);
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(0);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        } 
            //try {
          //writeToCSV(wordCount, String.format("test_%s", file.getName());// throw new IOException();  
            ////} catch (IOException e) {}
        }
        writeToCSV(wordCountAll, "all_test.csv");

    }
    public static void writeToCSV(HashMap<String,Integer> words, String filename) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String dir = "counted_words";
        new File(String.format("%s%s", absolutePath, dir)).mkdirs();
        try (OutputStreamWriter file = new OutputStreamWriter(
        new FileOutputStream(String.format("%s%s%s%s%s",absolutePath,sep,dir,sep,filename)),
        Charset.forName("UTF-8").newEncoder());) {
                for (Map.Entry<String,Integer> word: words.entrySet()) {
                    file.write(String.format("%s;%d\n",word.getKey(),word.getValue()));
                }
                file.close();
            } catch (IOException e) {
                System.err.println(e);
                System.exit(0);
            }
    }
}
