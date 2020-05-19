package com.example.rxjavapractice.network

import com.example.rxjavapractice.models.Movie
import com.example.rxjavapractice.models.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDB {

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Observable<Response<Movie>>

}
