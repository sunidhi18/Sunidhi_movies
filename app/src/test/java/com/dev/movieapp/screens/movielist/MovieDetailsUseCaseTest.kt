package com.dev.movieapp.screens.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.movieapp.base.BaseUTTest
import com.dev.movieapp.di.configureTestAppComponent
import com.dev.movieapp.network.movieslist.MoviesAPIService
import com.dev.movieapp.repository.MovieListRepository
import com.dev.movieapp.screens.movieslist.MovieListUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class MovieDetailsUseCaseTest : BaseUTTest(){

    //Target
    private lateinit var mMovieListUseCase: MovieListUseCase
    //Inject movieslist repo created with koin
    val mMovieListRepo : MovieListRepository by inject()
    //Inject api service created with koin
    val mAPIService : MoviesAPIService by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mCount = 20

    @Before
    fun start(){
        super.setUp()
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_movie_list_use_case_returns_expected_value()= runBlocking{

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mMovieListUseCase = MovieListUseCase()

        val dataReceived = mMovieListUseCase.processMovieListUseCase()

        assertNotNull(dataReceived)
        assertEquals(dataReceived.numResults, mCount)
    }

}