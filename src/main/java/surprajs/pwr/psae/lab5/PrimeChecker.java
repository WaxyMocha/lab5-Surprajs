package surprajs.pwr.psae.lab5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class PrimeChecker {
    
    public PrimeChecker(String possiblePrimeFile, String filename) {
        ArrayList<Long> primes = getPrimes(possiblePrimeFile);
        
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String dir = "primes";
        try (OutputStreamWriter file = new OutputStreamWriter(
        new FileOutputStream(String.format("%s%s%s%s%s",absolutePath,sep,dir,sep,filename)));) {
                for (long prime: primes) {
                    file.write(String.format("%d,\n", prime));
                }
                file.close();
            } catch (IOException e) {
                System.err.println(e);
                System.exit(0);
            }
    }
    public static ArrayList<Long> getPrimes(String possiblePrimeFile) {
        ArrayList<Long> primeList = new ArrayList<>();
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String dir = "primes";
        try (BufferedReader file = new BufferedReader(
            new InputStreamReader(
            new FileInputStream(String.format("%s%s%s%s%s",absolutePath,sep,dir,sep,possiblePrimeFile))));) {
            String read;
            while ((read = file.readLine()) != null) {
                long number = Long.parseLong(new String(read.getBytes()).replaceAll(",", ""));
                if (Primes.isPrime(number)) primeList.add(number);
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(0);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }        
        return primeList;
    }
}
