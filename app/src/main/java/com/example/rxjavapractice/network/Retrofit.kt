package com.example.rxjavapractice.network

import com.example.rxjavapractice.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    private val BASE_URL = BuildConfig.BASE_URL
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val dataManager: TheMovieDB = retrofit.create(TheMovieDB::class.java)
}