package com.dev.movieapp.di

import com.dev.movieapp.repository.MovieListRepository
import org.koin.dsl.module

/**
 * Repository DI module.
 * Provides Repo dependency.
 */
val RepoDependency = module {

    factory {
        MovieListRepository()
    }

}