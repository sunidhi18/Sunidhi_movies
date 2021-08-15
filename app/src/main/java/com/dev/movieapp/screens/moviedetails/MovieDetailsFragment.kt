package com.dev.movieapp.screens.moviedetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dev.movieapp.R
import com.dev.movieapp.models.movieslist.MovieResults
import com.dev.movieapp.platform.BaseFragment
import com.dev.movieapp.uigeneric.MovieDetailsAnimator
import kotlinx.android.synthetic.main.fragment_main_activity.*
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.landing_list_view_item.*

class MovieDetailsFragment : BaseFragment() {

    companion object{
        private const val MOVIE = "MOVIE"
        fun forMovie(movie: MovieResults?) = MovieDetailsFragment().apply {
            arguments = bundleOf(MOVIE to movie)
        }
    }

    private val viewModel: MovieDetailsActivityViewModel by lazy {
        ViewModelProvider(this).get(MovieDetailsActivityViewModel::class.java)
    }

    lateinit var movieDetailsAnimator: MovieDetailsAnimator

    override fun getLayoutId(): Int = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailsAnimator = MovieDetailsAnimator()
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderMovieDetails(arguments?.get(MOVIE) as MovieResults?)
    }

    private fun renderMovieDetails(movie: MovieResults?) {
        movieSummary.text = movie?.summary_short
        by_line.text = movie?.byline
        movieYear.text = movie?.publication_date.toString()

        movie?.multimedia?.width?.let {
            Glide.with(this@MovieDetailsFragment)
                    .load(movie.multimedia.src)
                    .override(it, movie.multimedia.height)
                    .error(R.drawable.ic_baseline_warning_24)
                    .into(moviePoster)
        }
        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
    }
}
