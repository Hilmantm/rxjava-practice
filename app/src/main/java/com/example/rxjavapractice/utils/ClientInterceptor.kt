package com.example.rxjavapractice.utils

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.lang.Exception

class ClientInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            return chain.proceed(request)
        } catch (e: Exception) {
            return Response.Builder()
                .request(request)
                .code(408)
                .protocol(Protocol.HTTP_1_1)
                .message("{\"status\":\"fail\",\"description\":\"Please check your connection\"}")
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                        "{\"status\":\"fail\",\"description\":\"Please check your connection\"}"
                    )
                )
                .build()
        }
    }
}