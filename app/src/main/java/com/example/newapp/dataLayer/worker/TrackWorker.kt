package com.example.newapp.dataLayer.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.DeadSystemException
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newapp.R
import com.example.newapp.presentation.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

@HiltWorker
class TrackWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    private val notificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val sensorManager =
        appContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    @Inject
    lateinit var stepListener: SensorEventListener
    private val NOTIFICATION_CHANNEL = "Track Service"
    private val NOTIFICATION_ID = 1001
    private val LISTEN_DURATION_MS = 8 * 60 * 1000L // 8 minutes max listening time

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d("TrackWorker", "Work started on Android ${Build.VERSION.SDK_INT}")

        createNotificationChannel()

        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepSensor == null) {
            Log.w("TrackWorker", "No step counter sensor found")
            return@withContext Result.success()
        }
        try {
            sensorManager.registerListener(
               stepListener ,
                stepSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            // Listen up to 8 minutes or until WorkManager stops it
            withTimeoutOrNull(LISTEN_DURATION_MS) {
                delay(LISTEN_DURATION_MS)
            }
        } catch (e: Exception) {
            Log.e("TrackWorker", "Error while listening to steps", e)
        } finally {
            try {
                sensorManager.unregisterListener(stepListener)
            } catch (e: Exception) {
                Log.e("TrackWorker", "Error unregistering listener", e)
            }
        }

        Result.success()
    }





    private fun buildNotification(steps: Int): Notification {

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(applicationContext, NOTIFICATION_ID, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        var content = "0"
        content = if(steps > 0){
            "$steps steps today"
        } else{
            "No steps recorded yet today."
        }

        return NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setColor(ContextCompat.getColor(applicationContext, R.color.purple_700)) // changes background tint (Android 8+)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(false)
            .build().apply {
                flags = flags or Notification.FLAG_NO_CLEAR or Notification.FLAG_ONGOING_EVENT
            }
    }

    private fun updateNotification(stepCount: Int) {
        try {
            notificationManager.notify(NOTIFICATION_ID, buildNotification(stepCount))
        } catch (e: DeadSystemException) {
            Log.w("TrackWorker", "System dead, skipping notification update")
        } catch (e: Exception) {
            Log.e("TrackWorker", "Failed to update notification", e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            NOTIFICATION_CHANNEL,
            "Track Service Channel",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            setShowBadge(false)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
        notificationManager.createNotificationChannel(serviceChannel)
    }
}
