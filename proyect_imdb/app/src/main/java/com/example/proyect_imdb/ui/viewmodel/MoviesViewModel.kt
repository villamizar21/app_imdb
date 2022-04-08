package com.example.proyect_imdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.proyect_imdb.data.remote.ApiService
import com.example.proyect_imdb.data.repository.MovieDatSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private var pagingSource: MovieDatSource? = null

    /**Si se necesita mas paginas se agregan aqui**/
    val flow = Pager(PagingConfig(pageSize = 20)) {
        MovieDatSource(api).also {
            pagingSource = it
        }
    }.flow
        .cachedIn(viewModelScope)

    //TODO USAR PARA REFRESCAR LOS DATOS
    fun clearData() = pagingSource?.invalidate()
}