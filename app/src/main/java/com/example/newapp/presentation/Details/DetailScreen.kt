package com.example.newapp.presentation.Details

import androidx.compose.runtime.Composable
import com.example.newapp.dataLayer.models.Character
import com.example.newapp.presentation.home.CharacterDetails

@Composable
fun DetailScreen(character: Character) {
    CharacterDetails(character)
}