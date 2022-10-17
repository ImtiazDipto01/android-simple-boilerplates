package com.example.androidsimpleboilerplates.core.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class SafeCacheStrategyHandler<RESULT, REQUEST> {

    inline fun <reified RESULT, reified REQUEST> get() = flow {

        // emit loading state
        emit(Resource.Loading())

        // Fetch latest posts from remote
        val apiResponse = fetchRemoteData()

        // parsed api response
        val data = handleApiResponse(apiResponse)

        // if the data matches with expected [REQUEST]
        if (data != null && data is REQUEST) {

            // map [REQUEST] response to [RESULT]
            val result = mapRequestToResult(data)

            // on [RESULT] valid save result to database
            if (result != null && result is RESULT)
                saveRemoteData(result)
        }
        // otherwise returning error state with error data
        else emit(Resource.Error(data as ErrorHandler))

        // Emit Database content
        emitAll(
            fetchLocalData().map {
                Resource.Success(it)
            }
        )

    }.flowOn(Dispatchers.IO).catch { exp -> emit(Resource.Error(getCustomErrorMessage(exp))) }


    /**
     * Fetches [Response] from the remote end point.
     */
    abstract suspend fun fetchRemoteData(): Response<REQUEST>

    /**
     * Map network response [REQUEST] and return as [RESULT]
     */
    abstract fun mapRequestToResult(request: Any): RESULT?

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    abstract suspend fun saveRemoteData(result: RESULT)

    /**
     * Retrieves all data from persistence storage.
     */
    abstract fun fetchLocalData(): Flow<RESULT>
}



