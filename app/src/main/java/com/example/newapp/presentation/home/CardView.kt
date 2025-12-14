package com.example.newapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newapp.dataLayer.models.Character

@Composable
fun CardView(character: Character, modifier: Modifier = Modifier, onClick :(Character)-> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(350.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            onClick(character)
        }
    ) {
        CharacterDetails(character)
    }
}

@Composable
fun CharacterDetails(character: Character) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Person Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.Black)
        )
        Text(
            text = "Hello ${character.name}!",
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(color = Color.DarkGray.copy(0.4f))
                .padding(vertical = 4.dp, horizontal = 12.dp)
                .align(Alignment.BottomCenter)
                .shadow(elevation = 5.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreCardView() {
    MaterialTheme {
        CardView(
            character = Character(
                "123",
                "Rishabh",
                "https://static.wikia.nocookie.net/disney/images/6/67/HATS_Achilles.png"
            )
        ){

        }
    }

}