package com.example.newapp.domain.repository

import android.util.Log
import com.example.newapp.data.models.CharacterList
import com.example.newapp.domain.AppError
import com.example.newapp.domain.NetworkError
import com.example.newapp.domain.Result
import com.example.newapp.domain.network.ApiService
import com.example.newapp.domain.network.NetworkApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BaseRepoImplementation() : IRepository {
    val apiService : ApiService by lazy {
        NetworkApiService.retrofitObj()
    }
    override suspend fun getCharactersList(): Flow<Result<CharacterList, AppError>> = flow{
        try{
            val response = apiService.getCharacters()
            Log.d("BaseRepoImplementation", "$response")
            emit(Result.Success(data = response))
        } catch (e : Error){
            e.printStackTrace()
            Log.e("NetworkError", "Error: ${e.localizedMessage}", e)
            emit(Result.Error(NetworkError(e.localizedMessage)))
        }

    }.flowOn(Dispatchers.IO)
}