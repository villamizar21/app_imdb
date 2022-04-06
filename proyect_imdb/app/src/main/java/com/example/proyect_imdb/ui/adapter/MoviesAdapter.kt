package com.example.proyect_imdb.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyect_imdb.R
import com.example.proyect_imdb.data.model.MovieResults
import com.example.proyect_imdb.databinding.ItemMoviesBinding
import com.example.proyect_imdb.util.ConstValues.IMAGE_URL

class MoviesAdapter(private val listener: ClickListener) :
    PagingDataAdapter<MovieResults, MoviesAdapter.HomeViewHolder>(MovieDiff) {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
       holder.itemView.setOnClickListener {
           listener.clicked(getItem(position)?.id)
       }
        Glide.with(holder.itemView).load(IMAGE_URL+getItem(position)?.posterpaht)
            .into(holder.itemView.findViewById(R.id.imageViewMovie))
        
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    class HomeViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    object MovieDiff : DiffUtil.ItemCallback<MovieResults>() {
        override fun areItemsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
            newItem == oldItem
    }
}

interface ClickListener {
    fun clicked(value: Int?)
}

