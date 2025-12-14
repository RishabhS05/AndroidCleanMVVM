package com.example.newapp.domain.repository

import com.example.newapp.dataLayer.models.CharacterList
import com.example.newapp.dataLayer.models.AppError
import kotlinx.coroutines.flow.Flow
import com.example.newapp.dataLayer.models.Result
interface IRepository {
    suspend fun getCharactersList() : Flow<Result<CharacterList,AppError>>
}
interface IRepositoryLiveData{
    suspend fun getCharactersListLiveData() : CharacterList
}