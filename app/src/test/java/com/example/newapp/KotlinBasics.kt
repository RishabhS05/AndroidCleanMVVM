package com.example.newapp

import org.junit.Test

class KotlinBasics {

    @Test
    fun runTests(){
        loops()
    }
    fun loops(){
        // loop 1
        println("i in 1..6 ")
        for (i in 1..6) {
            println(i)
        }

        // loop2 reverse
        println("reverse")
//      for (i in <length or highest size> dowmTo <value> step <decrement> ){}
        for (i in 6 downTo 0 step 2) { println(i) }
        val numbers  = arrayOf(1,2,3,4,5,6,4,3,5,4)
        // with indices
        println("number indices")
        for(num in numbers.indices){ println("$num") }
        println("numbers withIndex()")
        for ((index, value) in numbers.withIndex()) {
            println("The step at $index is \"$value\"")
        }
    }
}