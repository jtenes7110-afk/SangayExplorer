package com.example.sangayexplorer.data.repository

import com.example.sangayexplorer.data.local.dao.RutaDao
import com.example.sangayexplorer.data.model.Ruta
import kotlinx.coroutines.flow.Flow

class RutaRepository(
    private val rutaDao: RutaDao
) {

    fun obtenerRutas(): Flow<List<Ruta>> {
        return rutaDao.obtenerRutas()
    }

    suspend fun insertarRutas(rutas: List<Ruta>) {
        rutaDao.insertarRutas(rutas)
    }
}