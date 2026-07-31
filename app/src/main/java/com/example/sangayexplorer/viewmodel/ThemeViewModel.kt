package com.example.sangayexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sangayexplorer.datastore.ThemePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val preferences =
        ThemePreferences(application)

    val darkMode =
        preferences.darkModeFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun cambiarModoOscuro(
        activo: Boolean
    ) {

        viewModelScope.launch {

            preferences.guardarModoOscuro(activo)

        }

    }

}