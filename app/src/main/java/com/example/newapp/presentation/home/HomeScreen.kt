package com.example.newapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newapp.dataLayer.models.Character
import com.example.newapp.dataLayer.models.CharacterList

data class HomeState(
    val characterList: CharacterList? = null,
    val error: String? = null
)

@Composable
fun HomeScreen(onNavigateToDetail: (Character) -> Unit = {}) {
    val viewmodel: HomeViewModel = viewModel()
    val state = viewmodel.state.collectAsStateWithLifecycle().value
    HomeScreenView(homeState = state, onNavigateToDetail)
}
@Composable fun HomeScreen2(onNavigateToDetail: (Character) -> Unit = {}){
    val viewModel : HomeViewModel = viewModel()
    val state = viewModel.characterLiveData.observeAsState()
    state.value?.let { HomeScreenView(it,onNavigateToDetail) }
}

@Composable
fun HomeScreenView(list: CharacterList, onNavigateTo: (Character) -> Unit = {}) {
    if (list.data.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(list.data) { character ->
                CardView(
                    character = character,
                    onClick = onNavigateTo
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No data found")
        }
    }
}

@Composable
fun HomeScreenView(homeState: HomeState, onNavigateTo: (Character) -> Unit = {}) {
    if (!homeState.characterList?.data.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize())  {
            items(homeState.characterList.data) { character ->
                CardView(
                    character = character,
                  onClick = onNavigateTo
                    )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No data found")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    MaterialTheme {
        HomeScreenView(homeState = HomeState())
    }
}