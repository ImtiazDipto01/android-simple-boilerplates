package com.example.androidsimpleboilerplates.core.extensions

sealed class Resource<T> {
  data class Success<T>(val data: T) : Resource<T>()
  data class Loading<T>(val data: T? = null) : Resource<T>()
  data class Error<T>(val error: ErrorHandler) : Resource<T>()
  data class Empty<T>(val data: T? = null) : Resource<T>()
}