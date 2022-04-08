package com.example.proyect_imdb.ui.viewmodel

import androidx.lifecycle.*
import com.example.proyect_imdb.data.model.MovieDetail
import com.example.proyect_imdb.data.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieDetailRepository) :
    ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun getDetail(movieId: Long): LiveData<MovieDetail> =
        repository.getMovieDetails(movieId)
            .catch { _error.postValue(true) }
            .asLiveData()
}