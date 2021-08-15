package com.dev.movieapp.screens.movieslist

import android.os.Bundle
import com.dev.movieapp.R
import com.dev.movieapp.platform.BaseActivity

/**
 * Activity holder for Movies List Flow.
 */
class MovieListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureAndShowFragment()
    }

    private fun configureAndShowFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.base_frame_layout) as MovieListActivityFragment?
        if(fragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.base_frame_layout, MovieListActivityFragment.getMainActivityFragment())
                .commit()
        }
    }
}
