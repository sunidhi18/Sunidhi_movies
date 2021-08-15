package com.dev.movieapp.screens.movieslist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.movieapp.R
import com.dev.movieapp.models.movieslist.AllMovies
import com.dev.movieapp.models.movieslist.MovieResults
import com.dev.movieapp.platform.BaseFragment
import com.dev.movieapp.platform.BaseViewModelFactory
import com.dev.movieapp.platform.LiveDataWrapper
import com.dev.movieapp.screens.moviedetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.fragment_main_activity.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

/**
 * Movies List Fragment.
 * Handles UI.
 * Fires rest api initiation.
 */
class MovieListActivityFragment : BaseFragment() {

    companion object{
        fun getMainActivityFragment() = MovieListActivityFragment()
    }

    //---------------Class variables---------------//

    val mMovieListUseCase : MovieListUseCase by inject()
    private val mBaseViewModelFactory : BaseViewModelFactory =
        BaseViewModelFactory(Dispatchers.Main, Dispatchers.IO,mMovieListUseCase)
    private val TAG = MovieListActivityFragment::class.java.simpleName
    lateinit var mRecyclerViewAdapter: MovieListRecyclerViewAdapter

    private val mViewModel : MovieListActivityViewModel by lazy {
        ViewModelProviders.of(this,mBaseViewModelFactory)
            .get(MovieListActivityViewModel::class.java)    }

    private fun adapterOnClick(result: MovieResults) {
        val intent = Intent(context, MovieDetailsActivity()::class.java)
        intent.putExtra("MOVIE", result)
        this.startActivity(intent)
        Toast.makeText(context, result.display_title, Toast.LENGTH_SHORT).show()
    }

    //---------------Life Cycle---------------//

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Start observing the targets
        this.mViewModel.mAllPeopleResponse.observe(viewLifecycleOwner, this.mDataObserver)
        this.mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)

        mRecyclerViewAdapter = MovieListRecyclerViewAdapter (activity!!, arrayListOf()) {
            model -> adapterOnClick(model)
        }
        landingListRecyclerView.adapter = mRecyclerViewAdapter
        landingListRecyclerView.layoutManager = LinearLayoutManager(activity!!)

        this.mViewModel.requestMoviesListActivityData()

    }

    //---------------Observers---------------//
    private val mDataObserver = Observer<LiveDataWrapper<AllMovies>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                // Error for http request
                logD(TAG,"LiveDataResult.Status.ERROR = ${result.response}")
                error_holder.visibility = View.VISIBLE
                showToast("Error in getting data")

            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                logD(TAG,"LiveDataResult.Status.SUCCESS = ${result.response}")
                val mainItemReceived = result.response as AllMovies
                val  listItems =  mainItemReceived.results as ArrayList<MovieResults>
                processData(listItems)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_main_activity

    /**
     * Handle success data
     */
    private fun processData(listItems: ArrayList<MovieResults>) {
        logD(TAG,"processData called with data ${listItems.size}")
        logD(TAG,"processData listItems =  ${listItems}")

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            mRecyclerViewAdapter.updateListItems(listItems)
        }
    }

    /**
     * Hide / show loader
     */
    private val loadingObserver = Observer<Boolean> { visible ->
        // Show hide progress bar
        if(visible){
            progress_circular.visibility = View.VISIBLE
        }else{
            progress_circular.visibility = View.INVISIBLE
        }
    }
}
