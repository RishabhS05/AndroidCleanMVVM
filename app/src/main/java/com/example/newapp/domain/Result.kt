package com.example.newapp.domain

interface AppError

sealed interface Result<out D, out E: AppError> {
    data class Success<T>(val data : T) : Result<T, Nothing>
    data class Error< out E: AppError>(val error: E ) : Result<Nothing, E>

    data object Loading : Result<Nothing, Nothing>
}

data class NetworkError(val message :  String) : AppError
//
//inline fun <T, E: AppError> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
//    return when(this) {
//        is Result.Error -> this
//        is Result.Success -> {
//            action(data)
//            this
//        }
//        is Loading -> Loading
//    }
//}
//inline fun <T, E: AppError>Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
//    return when(this) {
//        is Result.Error -> {
//            action(error)
//            this
//        }
//        is Success -> this
//
//        is Loading -> Result.Loading
//    }