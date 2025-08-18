package com.example.newapp

import kotlin.math.max
/**
 * You are given an integer array nums. A subarray is a contiguous part of the array.
Your task is to find the subarray with the largest sum and return that sum.
* Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *Output: 6
 * */
fun main(){
    var arr = arrayOf(5, 4, -1, 7, 8)
    var arr2 = arrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
    var arr3 = arrayOf(-2, -3, -1, -4)
    maxSubArrayCount(arr)
    maxSubArrayCount(arr2)
    maxSubArrayCount(arr3)

maxSubArray(arr).apply {
   first.apply { print("$this :[" ) }
    second.forEach {
        print("$it,")
    }
    print("]\n")
    }
maxSubArray(arr2).apply {
    first.apply { print("$this :[" ) }
    second.forEach {
        print("$it,")
    }
    print("]\n")

}
maxSubArray(arr3).apply {
    first.apply { print("$this :[" ) }
    second.forEach {
        print("$it,")
    }
    print("]\n")
}
}
fun maxSubArrayCount(array : Array<Int>){
    var maxSum = array[0]
    var curSum = array[0]

for ((index,a) in array.withIndex()){
    if (index > 0 ) {
        println(" cur :${curSum} a+cur : ${curSum + a}")
        curSum = max(a, curSum + a)
        maxSum = max(maxSum, curSum)
    }
}
    println("Max Sum = $maxSum")
}
fun maxSubArray(array : Array<Int>): Pair<Int, Array<Int>> {
    var curSum = array[0]
    var maxSum = array[0]
    var start= 0
    var bestEnd =0
    var bestStart = 0
    for (index in 1 until array.size){
        if(array[index] >curSum + array[index]){
            start = index
            curSum = array[index]
        }
        else {
            curSum += array[index]
        }
        if(curSum > maxSum){
            maxSum = curSum
            bestEnd = index
            bestStart = start
        }
    }
      return Pair(maxSum,array.sliceArray(bestStart..bestEnd))

}