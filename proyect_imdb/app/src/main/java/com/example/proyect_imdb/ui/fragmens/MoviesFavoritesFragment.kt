package com.example.proyect_imdb.ui.fragmens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.proyect_imdb.R
import com.example.proyect_imdb.data.model.MovieResults
import com.example.proyect_imdb.databinding.FragmentMoviesBinding
import com.example.proyect_imdb.ui.ClickListener
import com.example.proyect_imdb.util.ConstValues


class MoviesFavoritesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {

    }

    private fun errorOperation() {
        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        // activity?.onBackPressed()
    }

}