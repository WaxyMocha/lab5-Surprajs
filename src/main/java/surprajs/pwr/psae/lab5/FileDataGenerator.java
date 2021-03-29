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
import java.util.ArrayList;
import java.util.Random;

public class FileDataGenerator {
    
    public FileDataGenerator(int numberOfFiles, String dir) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        ArrayList<String> listNames = loadFromFile(String.format("%spossible_names%snames.txt",absolutePath,sep));
        ArrayList<String> listSurnames = loadFromFile(String.format("%spossible_names%ssurnames.txt",absolutePath,sep));
        new File(String.format("%s%s", absolutePath, dir)).mkdirs();
        
        for (int i = 0; i < numberOfFiles; ++i) {
            try (OutputStreamWriter file = new OutputStreamWriter(
                    new FileOutputStream(String.format("%s%s%slist%d.txt",absolutePath,dir,sep,i+1)),
                    Charset.forName("UTF-8").newEncoder());) {
                int personsInFile = new Random().nextInt(10)+1;
                file.write("Name,Surname,Age,Salary\n");
                for (int j = 0; j < personsInFile; ++j) {
                    String name = listNames.get(new Random().nextInt(listNames.size()));
                    String surname = listSurnames.get(new Random().nextInt(listSurnames.size()));
                    int age = Person.randAge();
                    double salary = Person.randSalary();
                    file.write(String.format("%s,%s,%d,%.2f\n", name,surname,age,salary));
                }
                file.close();
            } catch (IOException e) {
                    System.err.println(e);
                    System.exit(0);
            }
        }
    }    
    
    
    
    public static int randAge() {
        Random rand = new Random();
        return rand.nextInt(50)+21;
    }
    
    static double randSalary() {
        return new Random().nextDouble()*10000;
    }
    
    public static ArrayList<String> loadFromFile(String filepath) {
        ArrayList<String> aList = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(filepath),
                    Charset.forName("UTF-8").newDecoder()));) {
            String read;
            while ((read = file.readLine()) != null) {
                aList.add(new String(read.getBytes()));
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(0);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }
        return aList;
    }
}
