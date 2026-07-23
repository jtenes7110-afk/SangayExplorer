package com.example.sangayexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sangayexplorer.data.repository.RutaRepository

class AddRouteViewModelFactory(
    private val repository: RutaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AddRouteViewModel::class.java)) {
            return AddRouteViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}