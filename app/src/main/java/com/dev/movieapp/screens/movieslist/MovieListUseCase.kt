package com.dev.movieapp.screens.movieslist

import com.dev.movieapp.models.movieslist.AllMovies
import com.dev.movieapp.repository.MovieListRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Use Case class for handling Movies List flow.
 * Requests data from Repo.
 * Process received data into required model and reverts back to ViewModel.
 */
class MovieListUseCase : KoinComponent {

    val mMovieListRepo : MovieListRepository by inject()

    suspend fun processMovieListUseCase() : AllMovies {
        for (x in 0 until 3){
            println(" Pre Data manipulation $x")
        }
        val response =  mMovieListRepo.getMoviesListData()

        for (x in 0 until 3){
            println(" Post Data manipulation $x")
        }

        return response
    }
}