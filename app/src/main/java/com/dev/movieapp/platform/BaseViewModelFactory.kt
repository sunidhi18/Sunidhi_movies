package com.dev.movieapp.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.movieapp.screens.moviedetails.MovieDetailsActivity
import com.dev.movieapp.screens.moviedetails.MovieDetailsActivityViewModel
import com.dev.movieapp.screens.movieslist.MovieListUseCase
import com.dev.movieapp.screens.movieslist.MovieListActivityViewModel
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Base VM Factory for creation of different VM's
 */
class BaseViewModelFactory constructor(
    private val mainDispather: CoroutineDispatcher
    ,private val ioDispatcher: CoroutineDispatcher
    ,private val useCase: MovieListUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieListActivityViewModel::class.java) ||
            modelClass.isAssignableFrom(MovieDetailsActivity::class.java)) {
            if (modelClass.isAssignableFrom(MovieListActivityViewModel::class.java))
                MovieListActivityViewModel(mainDispather, ioDispatcher,useCase) as T
            else
                MovieDetailsActivityViewModel(mainDispather, ioDispatcher,useCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}