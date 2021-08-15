package com.dev.movieapp.screens.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.movieapp.base.BaseUTTest
import com.dev.movieapp.di.configureTestAppComponent
import com.dev.movieapp.models.movieslist.AllMovies
import com.dev.movieapp.platform.LiveDataWrapper
import com.dev.movieapp.screens.movieslist.MovieListActivityViewModel
import com.dev.movieapp.screens.movieslist.MovieListUseCase
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class MovieListActivityViewModelTest: BaseUTTest(){

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mMovieListActivityViewModel: MovieListActivityViewModel

    val mDispatcher = Dispatchers.Unconfined

    @MockK
    lateinit var mMovieListUseCase: MovieListUseCase

    @Before
    fun start(){
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_movie_list_view_model_data_populates_expected_value(){

        mMovieListActivityViewModel = MovieListActivityViewModel(mDispatcher,mDispatcher,mMovieListUseCase)
        val sampleResponse = getJson("success_resp_list.json")
        var jsonObj = Gson().fromJson(sampleResponse, AllMovies::class.java)
        //Make sure movieslist use case returns expected response when called
        coEvery { mMovieListUseCase.processMovieListUseCase() } returns jsonObj
        mMovieListActivityViewModel.mAllPeopleResponse.observeForever {  }

        mMovieListActivityViewModel.requestMoviesListActivityData()

        assert(mMovieListActivityViewModel.mAllPeopleResponse.value != null)
        assert(
            mMovieListActivityViewModel.mAllPeopleResponse.value!!.responseStatus
                == LiveDataWrapper.RESPONSESTATUS.SUCCESS)
        /*val testResult = mMovieListActivityViewModel.mAllPeopleResponse.value as LiveDataWrapper<AllMovies>
        assertEquals(testResult.response!!.next,mNextValue)*/
    }
}