package com.example.newapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlandromeUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /*
    * Write a routine to detect palindromes.

Palindromes are words that read the same forward and backward.

The input should be a String.

Examples:

eve

radar

kayak

racecar

Counterexamples:

write

detect

palindrome

 */
    @Test
    fun isStringPalindromeTest(){
        assertTrue("detect is not Palindrome String", isStringPalindrome("detect"))
        assertTrue("eve is Palindrome String", isStringPalindrome("eve"))
        assertTrue("radar is Palindrome String", isStringPalindrome("radar"))
        assertFalse("write is not Palindrome String", isStringPalindrome("write"))
    }
    fun isStringPalindrome(input : String) : Boolean{
        if (input.isBlank()) return false
        var reversed  = ""
        for(i in 0..input.length-1) {
            reversed += "${input[input.length-1-i]}"
        }
        if (input == reversed) return true
        return false
    }

    @Test
    fun isStringPalindrome2Test(){
        //      assertTrue("detect is not Palindrome String", isStringPalindrome2("detect"))
        assertTrue("eve is Palindrome String", isStringPalindrome2("eve"))
        assertTrue("radar is Palindrome String", isStringPalindrome2("radar"))
        assertFalse("write is not Palindrome String", isStringPalindrome2("write"))
        assertTrue("abba is Palindrome String", isStringPalindrome2("abba"))

    }
    fun isStringPalindrome2(input : String) : Boolean{
        if (input.isBlank()) return false
        var reversed  = ""
        var left = 0
        var right = input.length-1
        while (right >= left){
            if (input[left] == input[right]) {
                left+=1
                right-=1
            }
            else break
        }
        if(right == left) {
            return true
        }
        return false
    }
}

