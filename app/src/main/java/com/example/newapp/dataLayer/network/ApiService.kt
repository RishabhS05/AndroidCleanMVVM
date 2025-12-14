package com.example.newapp.dataLayer.network

import com.example.newapp.dataLayer.models.CharacterList
import com.example.newapp.dataLayer.network.APIConstants.CHARACTER_ENDPOINT
import retrofit2.http.GET


object APIConstants {
    const val BASE_URL = "https://api.disneyapi.dev/"
    const val CHARACTER_ENDPOINT = "character"
}


interface ApiService {
  @GET(CHARACTER_ENDPOINT)
  suspend fun getCharacters(): CharacterList
}
