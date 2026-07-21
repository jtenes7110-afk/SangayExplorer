package com.example.sangayexplorer.data.repository

import com.example.sangayexplorer.data.local.dao.RutaDao
import com.example.sangayexplorer.data.model.Ruta
import kotlinx.coroutines.flow.Flow

class RutaRepository(
    private val rutaDao: RutaDao
) {

    fun obtenerRutas(): Flow<List<Ruta>> =
        rutaDao.obtenerRutas()

    suspend fun obtenerRutaPorId(id: Int): Ruta? =
        rutaDao.obtenerRutaPorId(id)

    suspend fun insertarRuta(ruta: Ruta) =
        rutaDao.insertarRuta(ruta)

    suspend fun insertarRutas(rutas: List<Ruta>) =
        rutaDao.insertarRutas(rutas)

    suspend fun actualizarRuta(ruta: Ruta) =
        rutaDao.actualizarRuta(ruta)

    suspend fun eliminarRuta(ruta: Ruta) =
        rutaDao.eliminarRuta(ruta)

    suspend fun contarRutas(): Int =
        rutaDao.contarRutas()
}