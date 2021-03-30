package surprajs.pwr.psae.lab5;

public class Primes {
    public static boolean isPrime(long number) {
        if (number < 2) return false;
        for (long i = 2; i*i < number+1; ++i) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
