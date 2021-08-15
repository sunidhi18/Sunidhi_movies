package com.dev.movieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.movieapp.base.BaseUTTest
import com.dev.movieapp.di.configureTestAppComponent
import com.dev.movieapp.network.movieslist.MoviesAPIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class MovieListRepositoryTest : BaseUTTest(){

    //Target
    private lateinit var mRepo: MovieListRepository
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

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_movie_list_repo_retrieves_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = MovieListRepository()

        val dataReceived = mRepo.getMoviesListData()

        assertNotNull(dataReceived)
        assertEquals(dataReceived.numResults, mCount)
    }
}