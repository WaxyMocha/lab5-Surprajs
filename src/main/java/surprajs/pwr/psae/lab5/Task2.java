/*
lab5 task2
michał piechowski 253137
 */
package surprajs.pwr.psae.lab5;

public class Task2 {
    public static void main(String[] args) {
        PossiblePrimesGenerator p1 = new PossiblePrimesGenerator(1000, "possible_primes.csv");
        PrimeChecker p2 = new PrimeChecker("possible_primes.csv", "test.csv");
    }    
}
