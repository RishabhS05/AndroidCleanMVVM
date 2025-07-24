package com.example.newapp.domain.repository

import com.example.newapp.data.models.CharacterList
import com.example.newapp.domain.AppError
import kotlinx.coroutines.flow.Flow
import com.example.newapp.domain.Result
interface IRepository {
    suspend fun getCharactersList() : Flow<Result<CharacterList,AppError>>
}