package com.dev.movieapp.models.movieslist

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Parent Data class for sample response.
 */
data class AllMovies(

    @JsonProperty("num_results")
    val numResults : Int,

    @JsonProperty("status")
    val status : String,

    @JsonProperty("has_more")
    val has_more : Boolean,

    @JsonProperty("results")
    val results : List<MovieResults>

)