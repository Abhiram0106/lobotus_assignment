package com.github.abhiram0106.lobotus_assignment.core.util

import android.util.Log
import com.github.abhiram0106.lobotus_assignment.core.data.model.MyErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.isSuccess
import io.ktor.utils.io.CancellationException
import java.net.UnknownHostException

suspend inline fun <reified T : Any> HttpClient.handleApi(req: HttpRequestBuilder.() -> Unit): Result<T> {
    return try {
        val response = request(req)
        val statusCode = response.status.value

        if (response.status.isSuccess()) {
            val body = if (T::class == Unit::class) {
                Unit as T
            } else {
                response.body<T>()
            }
            Result.success(body)
        } else {
            val error = response.body<MyErrorResponse>()
            Log.e("HANDLE_API", "FAILURE $statusCode ${error.message}")
            Result.failure(
                NetworkException(
                    statusCode,
                    error.message
                )
            )
        }
    } catch (e: Exception) {
        Log.e(
            "HANDLE_API",
            "EXCEPTION class = ${e::class}, cause = ${e.cause}, message = ${e.message}"
        )

        when {
            e is CancellationException -> {
                throw e
            }
            e is ConnectTimeoutException ||
                    e is SocketTimeoutException ||
                    e is UnknownHostException ||
                    e is HttpRequestTimeoutException -> {
                Result.failure(UnableToReachServerException())
            }

            else -> {
                Result.failure(AnErrorHasOccurredException())
            }
        }
    }
}