package com.dev.movieapp.repository

import com.dev.movieapp.models.movieslist.AllMovies
import com.dev.movieapp.network.movieslist.MoviesAPIService
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Repository for Movies List Flow.
 * Requests data from either Network or DB.
 *
 */
class MovieListRepository : KoinComponent {

    val mMoviesAPIService: MoviesAPIService by inject()
    /**
     * Request data from backend
     */
    suspend fun getMoviesListData(): AllMovies {

        return processDataFetchLogic()

    }

    suspend fun processDataFetchLogic(): AllMovies{

        for (x in 0 until 3){
            println(" Data manipulation prior to REST API request if required $x")
        }

        val dataReceived = mMoviesAPIService.getMovieListData()

        for (x in 0 until 3){
            println(" Data manipulation post REST API request if required $x")
        }

        return dataReceived
    }


}