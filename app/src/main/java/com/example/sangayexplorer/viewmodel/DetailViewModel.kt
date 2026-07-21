package com.example.sangayexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sangayexplorer.data.model.Ruta
import com.example.sangayexplorer.data.repository.RutaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: RutaRepository
) : ViewModel() {

    private val _ruta = MutableStateFlow<Ruta?>(null)
    val ruta: StateFlow<Ruta?> = _ruta

    fun cargarRuta(id: Int) {

        viewModelScope.launch {

            _ruta.value = repository.obtenerRutaPorId(id)

        }

    }

}