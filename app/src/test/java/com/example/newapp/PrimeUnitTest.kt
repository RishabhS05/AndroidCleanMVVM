package com.example.newapp

import junit.framework.TestCase.assertFalse
import org.junit.Test
import kotlin.test.assertTrue

class PrimeUnitTest {

    @Test
    fun isPrimeTestFalse() {
        assertFalse("12 is not prime", isPrime(12))
        assertFalse("14 is not prime", isPrime(14))
    }

    @Test
    fun isPrimeTestTrue() {
        assertTrue(isPrime(11), "11 is prime")
        assertTrue(isPrime(5), "5 is prime")
        assertTrue(isPrime(7), "7 is prime")
        assertTrue(isPrime(2), "2 is prime")
    }


    fun isPrime(number: Int): Boolean {

        //Explanation
        //All primes > 3 can be written as 6k ± 1.
        //
        //So we skip checking even numbers and multiples of 3.
        //
        //We start from 5 and check i and i+2, increasing i by 6 each time.
        //
        //Loop condition is i * i <= number, i.e., up to √n.


// Time Complexity
//Still O(√n), but:
//
//Fewer iterations than the basic sqrt(n) loop.
//
//Checks roughly one-third as many numbers.
//
// Space Complexity
//O(1) – no extra space used.

        if (number <= 1) return false
        if (number <= 3) return true
        if (number % 2 == 0 || number % 3 == 0) return false

        var i = 5
        while (i * i <= number) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false
            }
            i += 6
        }
        return true
    }
}