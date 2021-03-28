package surprajs.pwr.psae.lab5;

public class Primes {
    public static boolean isPrime(long number) {
        if (number < 2) return false;
        for (int i = 2; i < Math.sqrt((double)number)+1; ++i) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
