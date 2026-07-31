package com.example.sangayexplorer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sangayexplorer.R
import com.example.sangayexplorer.data.model.Ruta
import com.example.sangayexplorer.data.repository.RutaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditRouteViewModel(
    private val repository: RutaRepository
) : ViewModel() {

    private val _ruta =
        MutableStateFlow<Ruta?>(null)

    val ruta: StateFlow<Ruta?> = _ruta

    private val _imagenSeleccionada =
        mutableIntStateOf(R.drawable.ic_launcher_foreground)

    val imagenSeleccionada: State<Int> = _imagenSeleccionada

    fun seleccionarImagen(imagen: Int) {
        _imagenSeleccionada.intValue = imagen
    }

    fun cargarRuta(id: Int) {

        viewModelScope.launch {

            val ruta = repository.obtenerRutaPorId(id)

            _ruta.value = ruta

            if (ruta != null) {
                _imagenSeleccionada.intValue = ruta.imagen
            }

        }

    }

    fun actualizarRuta(ruta: Ruta) {

        viewModelScope.launch {

            repository.actualizarRuta(
                ruta.copy(
                    imagen = _imagenSeleccionada.intValue
                )
            )

        }

    }

    fun actualizarRuta(

        nombre: String,
        ubicacion: String,
        duracion: String,
        dificultad: String,
        descripcion: String

    ) {

        viewModelScope.launch {

            _ruta.value?.let { rutaActual ->

                val rutaActualizada = rutaActual.copy(

                    nombre = nombre,
                    ubicacion = ubicacion,
                    duracion = duracion,
                    dificultad = dificultad,
                    descripcion = descripcion,
                    imagen = _imagenSeleccionada.intValue

                )

                repository.actualizarRuta(rutaActualizada)

            }

        }

    }

    fun eliminarRuta() {

        viewModelScope.launch {

            _ruta.value?.let { rutaActual ->

                repository.eliminarRuta(rutaActual)

            }

        }

    }

}