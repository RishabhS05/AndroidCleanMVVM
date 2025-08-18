package com.example.newapp
/**
 * Problem Statement:
 * Given an integer array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * ⚠️ Note:
 *
 * You must do this in-place (i.e., without making a copy of the array).
 *
 * Try to minimize the total number of operations.
 * Input  : [0, 1, 0, 3, 12]
 * output : [1, 3, 12, 0, 0]
 * Input  : [4, 2, 4, 0, 0, 3, 0, 5, 1, 0]
 * output : [4, 2, 4, 3, 5, 1, 0, 0, 0, 0]
 * */
fun main(){
    val numbers = arrayOf(0, 1, 0, 3, 12)
    val numbers1 = arrayOf(4, 2, 4, 0, 0, 3, 0, 5, 1, 0)
    moveZeros(numbers)
    moveZeros(numbers1)
}
fun moveZeros(numbers1: Array<Int>) {
    var nums = arrayOfNulls<Int>(numbers1.size)
    var lastNonZeroIndex = 0
    for (num in numbers1){
        if (num != 0) {
            nums[lastNonZeroIndex] = num
            lastNonZeroIndex++
        }
    }
    for (i in lastNonZeroIndex until numbers1.size){
        nums[i] = 0
    }
    print("[")
    for(n in nums){ print("$n,") }
    print("]")
}