package com.example.sangayexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sangayexplorer.data.repository.WeatherRepository
import com.example.sangayexplorer.network.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun obtenerClima() {

        viewModelScope.launch {

            _loading.value = true
            _error.value = null

            try {

                _weather.value = repository.obtenerClima(

                    latitud = -2.216,

                    longitud = -78.450

                )

            } catch (e: Exception) {

                _error.value = e.message

            } finally {

                _loading.value = false

            }

        }

    }

}