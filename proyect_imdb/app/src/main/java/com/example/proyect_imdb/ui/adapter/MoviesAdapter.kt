package com.example.proyect_imdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyect_imdb.R
import com.example.proyect_imdb.data.model.MovieResults
import com.example.proyect_imdb.ui.ClickListener
import com.example.proyect_imdb.util.ConstValues.IMAGE_URL

class MoviesAdapter(private val listener: ClickListener) :
    PagingDataAdapter<MovieResults, MoviesAdapter.ViewHolder>(MovieDiff) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var list:MovieResults

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movies, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.clicked(getItem(position)?.id)
        }
        holder.itemView.findViewById<CheckBox>(R.id.cbFavorite).setOnClickListener {
            //listener.favoriteClick(getItem(position)?.id)
        }
        Glide.with(holder.itemView).load(IMAGE_URL + getItem(position)?.posterpaht)
            .into(holder.itemView.findViewById(R.id.movie_poster))


        holder.itemView.findViewById<ProgressBar>(R.id.progress_vote).progress = getItem(position)?.voteAverage!!.toInt()

        holder.itemView.findViewById<TextView>(R.id.titleMovie).text = getItem(position)?.title

        holder.itemView.findViewById<TextView>(R.id.vote_average).text = getItem(position)?.voteAverage.toString()

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    object MovieDiff : DiffUtil.ItemCallback<MovieResults>() {
        override fun areItemsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
            newItem == oldItem
    }
}

