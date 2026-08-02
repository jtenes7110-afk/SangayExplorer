package com.example.sangayexplorer.viewmodel


import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sangayexplorer.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _latitud = MutableStateFlow<Double?>(null)
    val latitud: StateFlow<Double?> = _latitud

    private val _longitud = MutableStateFlow<Double?>(null)
    val longitud: StateFlow<Double?> = _longitud

    private val _distancia = MutableStateFlow<Double?>(null)
    val distancia: StateFlow<Double?> = _distancia

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun obtenerUbicacion() {

        viewModelScope.launch {

            _loading.value = true
            _error.value = null

            try {

                val location = repository.obtenerUbicacion()

                if (location != null) {

                    _latitud.value = location.latitude
                    _longitud.value = location.longitude

                    val usuario = Location("").apply {
                        latitude = location.latitude
                        longitude = location.longitude
                    }

                    val parque = Location("").apply {
                        latitude = -2.216
                        longitude = -78.450
                    }

                    val distanciaKm =
                        usuario.distanceTo(parque) / 1000

                    _distancia.value = distanciaKm.toDouble()

                } else {

                    _error.value = "No se pudo obtener la ubicación."

                }

            } catch (e: Exception) {

                _error.value = e.message

            } finally {

                _loading.value = false

            }

        }

    }
}