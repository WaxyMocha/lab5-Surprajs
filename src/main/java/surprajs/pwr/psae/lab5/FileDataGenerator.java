package surprajs.pwr.psae.lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class FileDataGenerator {
    private final static String CSV_SEP = ",";
    
    public FileDataGenerator(int numberOfFiles, String dir) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        ArrayList<String> listNames = loadFromFile(String.format("%spossible_names%snames.txt",absolutePath,sep));
        ArrayList<String> listSurnames = loadFromFile(String.format("%spossible_names%ssurnames.txt",absolutePath,sep));
        new File(String.format("%s%s", absolutePath, dir)).mkdirs();
        for (int i = 0; i < numberOfFiles; ++i) {
            OutputStreamWriter file = null;
            try {
                file = new OutputStreamWriter(
                    new FileOutputStream(String.format("%s%s%slist%d.csv",absolutePath,dir,sep,i+1)),
                    Charset.forName("UTF-8").newEncoder());
                int personsInFile = new Random().nextInt(10)+1;
                file.write(String.format("Name%1$sSurname%1$sAge%1$sSalary\n",CSV_SEP));
                for (int j = 0; j < personsInFile; ++j) {
                    String name = listNames.get(new Random().nextInt(listNames.size()));
                    String surname = listSurnames.get(new Random().nextInt(listSurnames.size()));
                    int age = Person.randAge();
                    double salary = Person.randSalary();
                    file.write(String.format("%2$s%1$s%3$s%1$s%4$d%1$s%5$.2f\n", CSV_SEP,name,surname,age,salary));
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
    
    public static ArrayList<String> loadFromFile(String filepath) {
        ArrayList<String> aList = new ArrayList<>();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), Charset.forName("UTF-8").newDecoder()));
            String read;
            while ((read = file.readLine()) != null) {
                aList.add(new String(read.getBytes()));
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
        return aList;
    }
} 