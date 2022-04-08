package com.example.proyect_imdb.ui.fragmens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyect_imdb.util.hideKeyboard
import com.example.proyect_imdb.R
import com.example.proyect_imdb.databinding.FragmentMoviesBinding
import com.example.proyect_imdb.ui.ClickListener
import com.example.proyect_imdb.ui.adapter.MoviesAdapter
import com.example.proyect_imdb.ui.viewmodel.MoviesViewModel
import com.example.proyect_imdb.ui.viewmodel.SearchViewModel
import com.example.proyect_imdb.util.ConstValues.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentMoviesBinding

    private var moviesAdapter: MoviesAdapter = MoviesAdapter(this)
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()

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
        buscarMovie()
    }

    private fun setAdapter() {
        val  layoutManager = if(resources.getBoolean(R.bool.isTablet)) {
            GridLayoutManager(context, 4)
        }else
            GridLayoutManager(context, 2)

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

    private fun buscarMovie() {
        binding.buscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0!!.isEmpty()) {
                    getData()
                    activity?.hideKeyboard()
                } else {
                    gettingDataSearch(binding.buscar.text.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun gettingDataSearch(searchQuery: String) {
        lifecycleScope.launch {
            searchViewModel.searchMovie(searchQuery).collectLatest { pagingData ->
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