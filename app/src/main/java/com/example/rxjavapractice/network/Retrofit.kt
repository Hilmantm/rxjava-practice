package com.example.rxjavapractice.network

import com.example.rxjavapractice.utils.ClientInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InterruptedIOException
import java.lang.Exception
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class  Retrofit<T : Any?>(url: String, apiService: Class<T>) {

    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(2000, TimeUnit.MILLISECONDS)
        .addInterceptor(ClientInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val dataManager: T = retrofit.create(apiService)
}