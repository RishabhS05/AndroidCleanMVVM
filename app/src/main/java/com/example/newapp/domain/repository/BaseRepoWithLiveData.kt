package com.example.newapp.domain.repository

import com.example.newapp.dataLayer.models.CharacterList
import com.example.newapp.dataLayer.network.ApiService
import com.example.newapp.dataLayer.network.NetworkApiService

class BaseRepoWithLiveData : IRepositoryLiveData {
    val apiService : ApiService by lazy {
        NetworkApiService.retrofitObj()
    }
    override suspend fun getCharactersListLiveData(): CharacterList = apiService.getCharacters()
}