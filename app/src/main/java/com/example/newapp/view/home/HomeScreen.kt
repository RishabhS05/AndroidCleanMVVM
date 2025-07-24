package com.example.newapp.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newapp.data.models.Character
import com.example.newapp.data.models.CharacterList

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

@Composable
fun HomeScreenView(homeState: HomeState, onNavigateTo: (Character) -> Unit = {}) {
    if (!homeState.characterList?.data.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
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