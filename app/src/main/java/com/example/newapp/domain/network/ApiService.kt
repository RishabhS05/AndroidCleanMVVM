package com.example.newapp.domain.network

import com.example.newapp.data.models.CharacterList
import com.example.newapp.domain.network.APIConstants.CHARACTER_ENDPOINT
import retrofit2.http.GET


object APIConstants {
    const val BASE_URL = "https://api.disneyapi.dev/"
    const val CHARACTER_ENDPOINT = "character"
}


interface ApiService {
  @GET(CHARACTER_ENDPOINT)
  suspend fun getCharacters(): CharacterList
}
