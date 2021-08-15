package com.dev.movieapp.network.movieslist

import com.dev.movieapp.BuildConfig
import com.dev.movieapp.models.movieslist.AllMovies
import retrofit2.http.GET

/**
 * Movie service Retrofit API.
 */
interface MoviesAPIService{

    @GET("reviews/picks.json?api-key=" + BuildConfig.API_KEY)
    suspend fun getMovieListData(): AllMovies

}