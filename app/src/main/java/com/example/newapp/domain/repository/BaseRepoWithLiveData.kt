package com.example.newapp.domain.repository

import com.example.newapp.data.models.CharacterList
import com.example.newapp.domain.network.ApiService
import com.example.newapp.domain.network.NetworkApiService

class BaseRepoWithLiveData : IRepositoryLiveData {
    val apiService : ApiService by lazy {
        NetworkApiService.retrofitObj()
    }
    override suspend fun getCharactersListLiveData(): CharacterList = apiService.getCharacters()
}