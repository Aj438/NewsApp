package com.android.newsapp.utils

sealed interface NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val message: String) : NetworkResult<Nothing>
}