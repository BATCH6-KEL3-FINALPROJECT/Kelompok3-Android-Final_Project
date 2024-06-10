package com.project.skypass.utils

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import org.json.JSONObject
import java.net.UnknownHostException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = try {
            chain.proceed(chain.request())
        } catch (e: UnknownHostException) {
            throw NoInternetException("No internet connection")
        }

        if (!response.isSuccessful) {
            val errorBody = response.body?.string()
            val errorMessage = parseErrorMessage(errorBody)
            throw HttpException(response.code, errorMessage)
        }

        return response
    }

    private fun parseErrorMessage(errorBody: String?): String {
        return try {
            val jsonObject = JSONObject(errorBody ?: "")
            if (jsonObject.getString("is_success") == "false") {
                jsonObject.getString("message")
            } else {
                "Unknown error"
            }
        } catch (e: Exception) {
            "Unknown error"
        }
    }

    class HttpException(val code: Int, message: String) : IOException(message)
    class NoInternetException(message: String) : IOException(message)
}
