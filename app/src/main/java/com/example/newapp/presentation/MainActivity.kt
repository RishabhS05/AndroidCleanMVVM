package com.example.newapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.newapp.dataLayer.models.Character
import com.example.newapp.dataLayer.worker.TrackWorker
import com.example.newapp.presentation.navigation.AppNavigation
import com.example.newapp.presentation.ui.theme.NewAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

//rerofit
//image
//navigation
//mVVm

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val WORK_NAME_PERIODIC = "sync_step_data_work"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        schedulePeriodicSync(this)
        setContent {
            NewAppTheme {
                Scaffold {innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
    fun schedulePeriodicSync(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .apply {
                // setRequiresDeviceIdle exists across Android versions via WorkManager API,
                // leaving false keeps behavior consistent
                setRequiresDeviceIdle(false)
            }
            .build()

        val periodicRequest = PeriodicWorkRequestBuilder<TrackWorker>(
            15, TimeUnit.MINUTES // ✅ Minimum interval enforced by WorkManager
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                10, TimeUnit.MINUTES // ✅ Retry after 10 min, exponential retry policy
            )
            .addTag(WORK_NAME_PERIODIC)
            .build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(
                WORK_NAME_PERIODIC,
                ExistingPeriodicWorkPolicy.KEEP, // ✅ Prevent duplicate scheduling
                periodicRequest
            )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
     val char = Character("123", "Rishabh","" )
    NewAppTheme {
    }
}