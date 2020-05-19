package com.example.rxjavapractice.network

import com.example.rxjavapractice.BuildConfig
import com.example.rxjavapractice.models.Movie
import com.example.rxjavapractice.models.Response
import io.reactivex.rxjava3.core.Observable

class TheMovieDBRepository {

    private val apiKey = BuildConfig.API_TOKEN
    private val dataManager = Retrofit(BuildConfig.BASE_URL, TheMovieDB::class.java).dataManager

    fun getPopularMovie(): Observable<Response<Movie>> = dataManager.getPopularMovie(apiKey)

}