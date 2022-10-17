package com.example.androidsimpleboilerplates.core.extensions

sealed class DataResource<T> {
    data class Success<T>(val data: T) : DataResource<T>()
    data class Error<T>(val error: ErrorHandler) : DataResource<T>()
}