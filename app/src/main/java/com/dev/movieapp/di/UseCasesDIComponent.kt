package com.dev.movieapp.di

import com.dev.movieapp.screens.movieslist.MovieListUseCase
import org.koin.dsl.module

/**
 * Use case DI module.
 * Provide Use case dependency.
 */
val UseCaseDependency = module {

    factory {
        MovieListUseCase()
    }
}