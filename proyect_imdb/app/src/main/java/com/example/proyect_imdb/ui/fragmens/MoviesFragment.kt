package com.example.proyect_imdb.ui.fragmens

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyect_imdb.R
import com.example.proyect_imdb.databinding.FragmentMoviesBinding
import com.example.proyect_imdb.ui.adapter.ClickListener
import com.example.proyect_imdb.ui.adapter.MoviesAdapter
import com.example.proyect_imdb.ui.viewmodel.MoviesViewModel
import com.example.proyect_imdb.util.ConstValues.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Double.max
import java.lang.Long.max
import java.util.Collections.max

@AndroidEntryPoint
class MoviesFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAdapter()
        getData()

    }

    private fun setHomeAdapter() {
        val layoutManager = GridLayoutManager(context,2)
        binding.recyclerviewMovies.layoutManager = layoutManager
        moviesAdapter = MoviesAdapter(this)
        binding.recyclerviewMovies.setHasFixedSize(true)
        binding.recyclerviewMovies.adapter = moviesAdapter
    }

    private fun getData() {
        lifecycleScope.launch {
            moviesViewModel.flow.collectLatest { pagingData ->
                moviesAdapter.submitData(pagingData)
            }
        }

    }

    override fun clicked(value: Int?) {
        val movieId = Bundle()
        movieId.putInt(MOVIE_ID, value ?: 0)

        findNavController().navigate(R.id.action_moviesFragment_to_detailsMovieFragment, movieId)
    }
}