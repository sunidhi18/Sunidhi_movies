package com.dev.movieapp.screens.movieslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.movieapp.R
import com.dev.movieapp.models.movieslist.MovieResults

/**
 * Adapter for Movies List screen recyclerview.
 */
class MovieListRecyclerViewAdapter(val context: Context, list: ArrayList<MovieResults>, private val listener: (MovieResults) -> Unit): RecyclerView.Adapter<MovieListRecyclerViewAdapter.MovieListFragViewHolder>() {

    var mItemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListFragViewHolder {
        return MovieListFragViewHolder(LayoutInflater.from(context).inflate(R.layout.landing_list_view_item,parent,false))
    }

    fun updateListItems(updatedList: ArrayList<MovieResults>){
        mItemList.clear()
        mItemList = updatedList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: MovieListFragViewHolder, position: Int) {
        val model : MovieResults = mItemList[position]
        holder.movieName.text = model.display_title
        holder.headLine.text = model.headline
        holder.itemView.setOnClickListener { listener(model) }
        model.multimedia?.width?.let {
            Glide.with(context)
                .load(model.multimedia.src)
                .override(it, model.multimedia.height)
                .error(R.drawable.ic_baseline_warning_24)
                .into(holder.movieImage)
        }
    }

    class MovieListFragViewHolder(item: View): RecyclerView.ViewHolder(item){
        val movieName : TextView = item.findViewById(R.id.movieName)
        val headLine : TextView = item.findViewById(R.id.movieHeadline)
        val movieImage : ImageView = item.findViewById(R.id.movieImage)
    }
}