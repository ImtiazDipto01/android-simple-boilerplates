package com.example.androidsimpleboilerplates.core.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class SafeApiCallStrategyHandler<RESULT, REQUEST> {

    inline fun <reified RESULT, reified REQUEST> get() = flow {

        // emit loading state
        emit(Resource.Loading())

        // Fetch latest posts from remote
        val apiResponse = fetchRemoteData()

        // parsed api response
        val data = handleApiResponse(apiResponse)

        // if the data matches with expected [REQUEST]
        if (data is REQUEST) {
            // map [REQUEST] response to [RESULT]
            val result = mapRequestToResult(data!!)

            // emit result
            if (result is RESULT)
                emit(Resource.Success(result))
        }
        // otherwise returning error state with error data
        else emit(Resource.Error(data as ErrorHandler))


    }.flowOn(Dispatchers.IO).catch { exp -> emit(Resource.Error(getCustomErrorMessage(exp))) }


    /**
     * Fetches [Response] from the remote end point.
     */
    abstract suspend fun fetchRemoteData(): Response<REQUEST>

    /**
     * Map network response [REQUEST] and return as [RESULT]
     */
    abstract fun mapRequestToResult(request: Any): RESULT?


}



