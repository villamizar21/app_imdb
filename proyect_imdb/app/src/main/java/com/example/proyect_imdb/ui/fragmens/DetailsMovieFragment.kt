package com.example.proyect_imdb.ui.fragmens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.proyect_imdb.R
import com.example.proyect_imdb.databinding.FragmentDetailsMovieBinding
import com.example.proyect_imdb.ui.viewmodel.MovieDetailsViewModel
import com.example.proyect_imdb.util.ConstValues.IMAGE_URL
import com.example.proyect_imdb.util.ConstValues.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsMovieBinding
    private val movieViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsMovieBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        val backdrop = binding.imageViewBackdropPath
        val movieId = arguments?.getLong(MOVIE_ID) ?: 0

        Log.e("", "epa mlp llege algo---------------------> $movieId")

        if (movieId == 0L)
            errorOperation()

        movieViewModel.getDetail(movieId).observe(viewLifecycleOwner, { movie ->

            Glide.with(this).load(IMAGE_URL + movie.backdropPath).into(backdrop)
            binding.materialTextViewMovieTitle.text = movie.title
            binding.materialTextViewOverview.text = movie.overview
            binding.materialTextViewVoteCount.text = movie.voteCount.toString()
            binding.materialTextViewDate.text = movie.releaseDate
            binding.materialTextViewPopularity.text = movie.popularity.toString()
        })

        movieViewModel.error.observe(viewLifecycleOwner, { isError ->
            if (isError) {
                errorOperation()
            }
        })
    }

    private fun errorOperation() {
        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
       // activity?.onBackPressed()
    }
}