package com.example.androidsimpleboilerplates.core.extensions

import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.io.FileNotFoundException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Safe api call helps to process a network call
 * and return a flow and emit corresponding data
 * from that Api call with State by depending on Success, Loading, Error State
 *
 * @param T -> a generic State base data that going to be contain successful Api response or error data
 * @param processApiCall -> contain a higher order suspended function
 * @receiver
 * @return -> return a flow and emit corresponding data from Api call with State
 */
suspend inline fun <reified REQUEST> safeApiCall(
    crossinline processApiCall: suspend () -> Response<REQUEST>
): Flow<Resource<REQUEST>> = flow {
    try {
        // emitting loading state
        emit(Resource.Loading())

        // calling api and processing the api response
        val response = processApiCall.invoke()
        val data = handleApiResponse(response)

        // if the data matches with the data we're expecting returning success state with that response
        // otherwise returning error state with error data
        if (data is REQUEST) emit(Resource.Success(data))
        else emit(Resource.Error(data as ErrorHandler))
    } catch (e: Exception) {
        emit(Resource.Error(getCustomErrorMessage(e)))
    }
}.flowOn(Dispatchers.IO)

/**
 * Handle api response helps to parse the response as expected result and return.
 * If failed to parse then returns error.
 *
 * @param REQUEST -> expected data class
 * @param response -> contains api response
 * @return Any -> If a successful parse return expected result, if error return error result
 */
fun <REQUEST> handleApiResponse(response: Response<REQUEST>): Any? {
    return try {
        // Parse body
        if (response.isSuccessful) response.body()
        else {
            // if anyhow got failed to parse Response, then trying to parse error response
            val json = response.errorBody()?.string()
            Gson().fromJson(json, ErrorHandler::class.java)
        }
    } catch (exp: Exception) {
        // In the meantime of parsing response any exception happened we're returning error
        exp.printStackTrace()
        ErrorHandler("Something Went Wrong!", JSONException(exp.message), response.code())
    }
}

/**
 * Any exception happen to call a api or emitting data then return custom error message
 *
 * @param error -> contains throwable exception
 * @return [ErrorHandler] with custom error message and exception
 */
fun getCustomErrorMessage(error: Throwable): ErrorHandler {
    return when (error) {
        is SocketTimeoutException -> ErrorHandler(
            "Oh! We couldn't capture your request in time. Please try again.",
            error
        )
        is android.util.MalformedJsonException -> ErrorHandler(
            "Oh! We hit an error. Try again later.",
            error
        )
        is FileNotFoundException -> ErrorHandler(
            "Oh! No such file or directory",
            error
        )
        is IOException -> ErrorHandler(
            "Oh! You are not connected to a wifi or cellular data network. Please connect and try again",
            error
        )
        is HttpException -> ErrorHandler(error.message(), error)

        else -> ErrorHandler("Something Went Wrong!!", error as Exception)
    }
}