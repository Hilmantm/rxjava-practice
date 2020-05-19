package com.example.rxjavapractice.utils

import android.util.Log
import com.example.rxjavapractice.base.BaseException
import retrofit2.HttpException

class ErrorHandler {
    companion object {

        fun handleError(throwable: Throwable?): String {
            if (throwable == null) {
                Log.d("Connection", "Error device network")
                return "Please check your network and try again."
            }

            if (throwable is BaseException) {

                val baseException = throwable as BaseException?
                val baseResponse = baseException?.response
                if (baseResponse != null) {
                    return baseResponse.description.toString()
                }
            }

            if (throwable is HttpException) {
                val httpException = throwable as HttpException?
                val errorCode = httpException?.code()?.or(0)
                if (errorCode == 401 || errorCode == 403) {
                    return "Please logout then re login"
                } else if (errorCode in 400..499) {
                    return "Please update to latest application"
                } else if (errorCode in 500..599) {
                    return "Internal server error, please try again later"
                }
            }

            return "Please check your network and try again."
        }
    }
}