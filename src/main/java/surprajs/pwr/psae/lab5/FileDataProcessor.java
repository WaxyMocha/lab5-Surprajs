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


public class FileDataProcessor {
    private ArrayList<People> listOfPeople = new ArrayList<>();

    public FileDataProcessor(String path) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        File dir = new File(String.format("%s%s",absolutePath, path));
        File[] filesInDirectory = dir.listFiles();
        int counter = 0;
        for (File filepath: filesInDirectory) { 
            try (BufferedReader file = new BufferedReader(
            new InputStreamReader(
            new FileInputStream(filepath),
            Charset.forName("UTF-8").newDecoder()));) {        
                String read;
                listOfPeople.add(new People());
                while ((read = file.readLine()) != null) {
                    try {
                        String str = new String(read.getBytes());
                        String[] str2 = str.split(",");
                        String newName = str2[0];
                        String newSurname = str2[1];
                        int newAge = Integer.parseInt(str2[2]);
                        double newSalary = Double.parseDouble(str2[3].replace(",","."));
                        listOfPeople.get(listOfPeople.size()-1).addPerson(newName,newSurname,newAge,newSalary);
                    } catch (NumberFormatException e) {}
                }
                file.close();
                double averageSalary = listOfPeople.get(listOfPeople.size()-1).getAverageSalary();
                String contentToWrite = String.format("In file %d,%.2f\n",listOfPeople.size(),averageSalary);
                int maxAge = listOfPeople.get(listOfPeople.size()-1).getTheOldestPerson().getAge();
                String maxName = listOfPeople.get(listOfPeople.size()-1).getTheOldestPerson().getName();
                String contentToWrite2 = String.format("The oldest in file %d,%s,%d\n",listOfPeople.size(),maxName,maxAge);
                writeToCSV("results_salary.csv", contentToWrite, counter != 0);
                writeToCSV("results_age.csv", contentToWrite2, counter != 0);
                ++counter;
            } catch (FileNotFoundException e) {
                System.err.println(e);
                System.exit(0);
            } catch (IOException e) {
                System.err.println(e);
                System.exit(0);
            }
        }
        String contentToWrite = String.format("Total average, %.2f\n", getTotalAverageSalary());
        String contentToWrite2 = String.format("The oldest in total,%s,%d\n", getTheOldest().getName(), getTheOldest().getAge());

        writeToCSV("results_salary.csv", contentToWrite, counter != 0);
        writeToCSV("results_age.csv", contentToWrite2, counter != 0);
    }
    private double getTotalAverageSalary() {
        if (this.listOfPeople.isEmpty()) return 0.0;
        double totalSalary = 0;
        double totalNumber = 0;
        for (People p: this.listOfPeople) {
            totalSalary += p.getAverageSalary()*p.getSize();
            totalNumber += p.getSize();
        }
        return totalSalary/totalNumber;
    }
    private Person getTheOldest() {
        Person theOldest = this.listOfPeople.get(0).getPerson(0);
        int maxAge = theOldest.getAge();
        for (People ps: this.listOfPeople) {
            if (ps.getTheOldestPerson().getAge() > maxAge) {
                maxAge = ps.getTheOldestPerson().getAge();
                theOldest = ps.getTheOldestPerson();
            }
       }
        return theOldest;
    }
    
    public static void writeToCSV(String filepath, String content, boolean append) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String dir = "results_csv";
        new File(String.format("%s%s", absolutePath, dir)).mkdirs();
        try (OutputStreamWriter file = new OutputStreamWriter(
        new FileOutputStream(String.format("%s%s%s%s%s",absolutePath,sep,dir,sep,filepath),append),
        Charset.forName("UTF-8").newEncoder());) {
                file.write(content);   
                file.close();
            } catch (IOException e) {
                System.err.println(e);
                System.exit(0);
            }
    }
}        
