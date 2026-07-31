package com.example.sangayexplorer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sangayexplorer.R
import com.example.sangayexplorer.data.model.Ruta
import com.example.sangayexplorer.data.repository.RutaRepository
import kotlinx.coroutines.launch

class AddRouteViewModel(
    private val repository: RutaRepository
) : ViewModel() {

    private val _imagenSeleccionada =
        mutableIntStateOf(R.drawable.ic_launcher_foreground)

    val imagenSeleccionada: State<Int> = _imagenSeleccionada

    fun seleccionarImagen(imagenId: Int) {
        _imagenSeleccionada.intValue = imagenId
    }

    fun guardarRuta(
        nombre: String,
        ubicacion: String,
        duracion: String,
        dificultad: String,
        descripcion: String
    ) {

        viewModelScope.launch {

            val nuevoId = repository.contarRutas() + 1

            val ruta = Ruta(
                id = nuevoId,
                nombre = nombre,
                descripcion = descripcion,
                ubicacion = ubicacion,
                duracion = duracion,
                dificultad = dificultad,
                imagen = _imagenSeleccionada.intValue
            )

            repository.insertarRuta(ruta)

        }

    }

}