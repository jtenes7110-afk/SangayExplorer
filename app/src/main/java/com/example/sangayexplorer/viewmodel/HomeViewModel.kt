package com.example.sangayexplorer.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sangayexplorer.data.model.Ruta
import com.example.sangayexplorer.data.repository.RutaRepository

class HomeViewModel : ViewModel() {

    private val repository = RutaRepository()

    val rutas: List<Ruta> = repository.obtenerRutas()

}