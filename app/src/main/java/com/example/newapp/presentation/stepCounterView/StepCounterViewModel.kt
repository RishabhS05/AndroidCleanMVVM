package com.example.newapp.presentation.stepCounterView

import androidx.lifecycle.ViewModel
import com.example.newapp.dataLayer.models.StepCounterModel
import kotlinx.coroutines.flow.MutableStateFlow


interface HandleStepCounterIntent{
    data object StartTracker : HandleStepCounterIntent
}

data class StepCounterUIState(val stepCounterCurrent : StepCounterModel?=null )
class StepCounterViewModel (): ViewModel() {
    init {
        handleIntent(HandleStepCounterIntent.StartTracker)
    }
    val state =  MutableStateFlow<StepCounterUIState>(StepCounterUIState())
    fun handleIntent(intent: HandleStepCounterIntent){
        when(intent){
            HandleStepCounterIntent.StartTracker ->  {
                // Todo Start worker to get the start tracker
            }
        }
    }
}