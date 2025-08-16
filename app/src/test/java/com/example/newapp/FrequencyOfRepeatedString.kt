package com.example.newapp

import kotlin.test.Test

class FrequencyOfRepeatedString {
@Test
fun frequesncyOfRepeatedString(){
    /**
     * 1) use hash-map to track count of each letter
     * 2) for loop pe present character ki count save karna hashmap main
     * 3) case1 one agar bala ki first repeating character nikalo to kisi bhi char ki count 2 hua
     *           a) loop break kar ke return kar dena char
     *   case2 agar list out karna hai frequency to save karna sare character hash map main
     *
     * */
    val value = "flutter"
    println("check "+checkFirstRepeatedChar(value))
    printFrequrncyOfChar(value)
}

    fun checkFirstRepeatedChar(value : String) : String{
        val map = mutableMapOf<Char, Int>()
        if (value.isBlank()) return ""
        for (v in value){
            if(map.containsKey(v)){
                return v.toString();
            }else map[v] = 1
        }
        return ""
    }

    fun printFrequrncyOfChar(value : String) {
        val map = mutableMapOf<Char, Int>()
        if (value.isBlank()) return
        for(v in value){
            if(map.containsKey(v)){
                map[v] = map[v]!! + 1
            } else {
                map[v] = 1
            }
        }
        for((char,count) in map){
            println("$char -> $count")
        }
    }
}