package com.example.newapp.presentation.stepCounterView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StepCounterScreen() {
    val viewModelStepCounter : StepCounterViewModel = StepCounterViewModel()
    val state = viewModelStepCounter.state.collectAsState().value
    StepCounterScreenUI(state)
}

@Composable
private fun StepCounterScreenUI(state: StepCounterUIState,modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            if(state.stepCounterCurrent!=null)
            Column() {
                Text("${state.stepCounterCurrent?.dateTime}", modifier = Modifier.padding(vertical = 12.dp))
                Row(horizontalArrangement = Arrangement.SpaceAround){
                    Text("Steps: ${state.stepCounterCurrent?.totalDailySteps}" )
                    Text("Calories burnt: ${state.stepCounterCurrent?.calories}" )
                }
                Row {
                    val distance = state.stepCounterCurrent?.distanceCoveredInKms?:0
                    state.stepCounterCurrent?.distanceCoveredInKms?.let { Text("Distance:  ${state.stepCounterCurrent.distanceCoveredInKms} ${ if(it > 0)"km" else "kms"}" ) }
                    Text("Active Minutes: ${state.stepCounterCurrent?.activeMins} mins" )
                }
            }
            else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No Data available.")
                }
            }
        }
    }
}