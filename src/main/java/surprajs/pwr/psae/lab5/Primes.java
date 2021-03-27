/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surprajs.pwr.psae.lab5;

/**
 *
 * @author mopie
 */
public class Primes {
    public static boolean isPrime(long number) {
        if (number < 2) return false;
        for (int i = 2; i < Math.sqrt((double)number)+1; ++i) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
