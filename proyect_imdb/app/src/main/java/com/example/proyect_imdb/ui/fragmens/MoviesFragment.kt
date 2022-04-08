package com.example.proyect_imdb.ui.fragmens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyect_imdb.R
import com.example.proyect_imdb.databinding.FragmentMoviesBinding
import com.example.proyect_imdb.ui.ClickListener
import com.example.proyect_imdb.ui.adapter.MoviesAdapter
import com.example.proyect_imdb.ui.viewmodel.MoviesViewModel
import com.example.proyect_imdb.util.ConstValues.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentMoviesBinding

    private var moviesAdapter: MoviesAdapter = MoviesAdapter(this)
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
        setAdapter()
        getData()

    }

    private fun setAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = moviesAdapter
    }

    private fun getData() {
        lifecycleScope.launch {
            moviesViewModel.flow.collectLatest { pagingData ->
                moviesAdapter.submitData(pagingData)
            }
        }

    }

    override fun clicked(value: Long?) {
        val movieId = Bundle()
        movieId.putLong(MOVIE_ID, value ?: 0)
               findNavController().navigate(R.id.action_moviesFragment_to_detailsMovieFragment, movieId)
    }
}