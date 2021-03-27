
package surprajs.pwr.psae.lab5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ThreadLocalRandom;

public class PossiblePrimesGenerator {
    public PossiblePrimesGenerator(int number, String filename) {
        String sep = System.getProperty("file.separator");
        String absolutePath = System.getProperty("user.dir") + sep;
        String dir = "primes";
        new File(String.format("%s%s", absolutePath, dir)).mkdirs();
        try (OutputStreamWriter file = new OutputStreamWriter(
        new FileOutputStream(String.format("%s%s%s%s%s",absolutePath,sep,dir,sep,filename)));) {
                for (int i = 0; i < number; ++i) {
                    file.write(String.format("%d\n", randLongNumber()));
                }
                file.close();
            } catch (IOException e) {
                System.err.println(e);
                System.exit(0);
            }
    }
    public static long randLongNumber() {
        return ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
    }
}

    
