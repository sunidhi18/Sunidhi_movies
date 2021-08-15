package com.dev.movieapp.screens.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.movieapp.models.movieslist.AllMovies
import com.dev.movieapp.platform.LiveDataWrapper
import com.dev.movieapp.screens.movieslist.MovieListUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

class MovieDetailsActivityViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val useCase: MovieListUseCase
) : ViewModel(), KoinComponent {

    var mAllPeopleResponse = MutableLiveData<LiveDataWrapper<AllMovies>>()
    val mLoadingLiveData = MutableLiveData<Boolean>()
    private val job = SupervisorJob()

    init {
        //call reset to reset all VM data as required
        resetValues()
    }

    //Reset any member as per flow
    fun resetValues() {

    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}