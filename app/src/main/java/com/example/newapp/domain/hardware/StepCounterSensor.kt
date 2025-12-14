package com.example.newapp.domain.hardware

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.example.newapp.dataLayer.localdb.DatabaseManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StepCounterSensor(val databaseManager : DatabaseManager) : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val currentSteps = event.values[0].toInt()
            CoroutineScope(Dispatchers.IO).launch {
                handleStepEvent(currentSteps)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    private suspend fun handleStepEvent(serviceTotalSteps: Int) {
        val todaysSteps = getTodaysStepsCount(serviceTotalSteps)
        databaseManager.insertORUpdateSteps(steps = todaysSteps, serviceTotalSteps.toLong())
//        updateNotification(todaysSteps)
    }
    private suspend fun getTodaysStepsCount(serviceTotalSteps: Int): Int {
        val data = databaseManager.getCurrentDaySteps()
        return (if (null != data) {
            if ((serviceTotalSteps - data.totalStepsAfterRestart) < 0) data.totalDailySteps
            else ((serviceTotalSteps - data.totalStepsAfterRestart ) + data.totalDailySteps)
        } else 0).toInt()
    }
}

