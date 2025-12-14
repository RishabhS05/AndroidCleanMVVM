package com.example.newapp.dataLayer.localdb

import com.example.newapp.dataLayer.localdb.dao.StepCounterDatabase
import com.example.newapp.dataLayer.models.StepCounterModel
import com.example.newapp.dataLayer.models.calculateVitals
import com.example.newapp.domain.utils.getCurrentSimpleDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseManager @Inject constructor(private val database : StepCounterDatabase,) {

    suspend  fun getCurrentDaySteps() : StepCounterModel? =
        database.getStepCounterDao().getStepsByDate(date = getCurrentSimpleDate(), userId =  "")
    suspend fun getCurrentSteps(): Flow<StepCounterModel?> =
        database.getStepCounterDao().getStepsForDate(date = getCurrentSimpleDate(), userId = "")
            .flowOn(Dispatchers.IO)

//    suspend fun updatedDataAsSynced(data : STStepEntity){
//        val existing = database.getStepCounterDao().getStepsByDate(date = data.date, userId = data.userId)
//        if (existing != null) {
//            // Update steps
//            val updated = existing.copy(
//                isDataSynced = true
//            )
//            database.getStepCounterDao().update(updated)
//        }
//    }
    suspend fun insertORUpdateSteps(steps: Int, totalSensorStepCount : Long)  {
        val timeStamp = System.currentTimeMillis()
    val today = getCurrentSimpleDate()
        val existing = database.getStepCounterDao().getStepsByDate(date =  today, userId = "")
        if (existing != null) {
            // Update steps
            val updated = existing.copy(
                totalDailySteps = steps.toLong(),
                totalStepsAfterRestart = totalSensorStepCount,
                dateTime = timeStamp.toString(),
                userId = "",
            ).calculateVitals()
            database.getStepCounterDao().update(updated)
        } else {
            // Insert new record
            val newEntry = StepCounterModel(
                id = timeStamp.toString(),
                timestamp = timeStamp,
                totalDailySteps = steps.toLong(),
                totalStepsAfterRestart = totalSensorStepCount,
                dateTime = today,
                userId = "",
            ).calculateVitals()
            database.getStepCounterDao().insert(newEntry)
        }
    }
}