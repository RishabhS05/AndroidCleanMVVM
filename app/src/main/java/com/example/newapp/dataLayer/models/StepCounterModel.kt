package com.example.newapp.dataLayer.models

import androidx.room.Entity
import java.sql.Timestamp


const val  STEPS_PER_KM = 1243.0
const val KCAL_PER_STEP = 0.045
const val METERS_PER_STEP = 0.804
const val ACTIVE_MINUTE = 100 //  steps is equal to 1 active minute

@Entity(tableName = "steps")
data class StepCounterModel(
    val id: String?,
    val timestamp: Long,
    val totalStepsAfterRestart: Long,
    val totalDailySteps: Long,
    val dateTime: String,
    val calories : Float = 0f,
    val activeMins : Float= 0f,
    val distanceCoveredInKms : Float = 0f,
    val userId : String = "",
    )

fun StepCounterModel.calculateActiveMinutes() : StepCounterModel {
    println("mins long ${(totalDailySteps / ACTIVE_MINUTE).toLong()}")
    println("mins int ${(totalDailySteps / ACTIVE_MINUTE)}")
    val mins = (totalDailySteps / ACTIVE_MINUTE).toFloat()
    return   this.copy(activeMins = mins)
}
fun StepCounterModel.calculateCalories() : StepCounterModel {
    // Average calories burned per step is approximately 0.04 to 0.06 calories
    val cal = (totalDailySteps * KCAL_PER_STEP).toFloat()
    return   this.copy(calories = cal)
}

fun StepCounterModel.calculateDistanceInMeters() : StepCounterModel {
    val dist =  (totalDailySteps * METERS_PER_STEP).toFloat()
    return  this.copy(distanceCoveredInKms =  dist)
}
// calculate all vitals in one go calories , distance , active minutes
fun StepCounterModel.calculateVitals() : StepCounterModel {
    val mins = (totalDailySteps / ACTIVE_MINUTE).toFloat()  // active minutes
    val cal = (totalDailySteps * KCAL_PER_STEP).toFloat()  // calories
    val dist =  ((totalDailySteps * METERS_PER_STEP) / 1000).toFloat() /// distance in meters
    return  this.copy(calories = cal, distanceCoveredInKms = dist, activeMins = mins)
}
