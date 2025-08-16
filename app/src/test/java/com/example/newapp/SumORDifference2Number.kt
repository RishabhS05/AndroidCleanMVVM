package com.example.newapp
import org.junit.Test
import kotlin.math.abs
fun main(){
    SumOrDiffernceUnitTest()
}


    fun SumOrDiffernceUnitTest(){
        val arr = arrayOf(1,2,3,4,5,6,7,8,9,10)
        sumOrDifferenceInArray(arr= arr, 4)
//        sumOrCharGPT(arr= arr, 4)
    }
    fun sumOrDifferenceInArray(arr: Array<Int>, diff: Int ){
        // arr = [1,2,4,6,7,10]
        // find all pair of whose difference is 4
        // two variable method
        arr.sort()
        val paires = mutableListOf<Pair<Int, Int>>()
        var left = 0
        var right = 1
        while( left < arr.size && right  < arr.size){
            val target = arr[right] -  arr[left]
            println("diff = $target")
            when{
               target == diff -> {
                   paires += arr[left] to  arr[right]
                   println("(${arr[left]},${arr[right]})")
                   left++
                   right++
               }
                target < diff  -> { right++ }
                else -> {
                    left++
                    if(left == right) right++
                }
            }
        }
     for(pair in paires)  println("(${pair.first},${pair.second})")
    }