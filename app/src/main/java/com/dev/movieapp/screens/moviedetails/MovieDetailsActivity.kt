package com.dev.movieapp.screens.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dev.movieapp.R
import com.dev.movieapp.models.movieslist.MovieResults
import com.dev.movieapp.platform.BaseActivity
import com.dev.movieapp.screens.movieslist.MovieListActivityFragment

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "MOVIE"

        fun callingIntent(context: Context, movie: MovieResults) =
            Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(INTENT_EXTRA_PARAM_MOVIE, movie)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureAndShowFragment()
    }

    private fun configureAndShowFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.base_frame_layout) as MovieListActivityFragment?
        if(fragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.base_frame_layout, MovieDetailsFragment.forMovie
                    (intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE))).commit()
        }
    }
}
