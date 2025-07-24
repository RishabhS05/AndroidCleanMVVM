package com.example.newapp.view.Details

import androidx.compose.runtime.Composable
import com.example.newapp.data.models.Character
import com.example.newapp.view.home.CharacterDetails

@Composable
fun DetailScreen(character: Character) {
    CharacterDetails(character)
}