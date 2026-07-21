package com.example.sangayexplorer.data.local.dao

import androidx.room.*
import com.example.sangayexplorer.data.model.Ruta
import kotlinx.coroutines.flow.Flow

@Dao
interface RutaDao {

    @Query("SELECT * FROM rutas ORDER BY nombre ASC")
    fun obtenerRutas(): Flow<List<Ruta>>

    @Query("SELECT * FROM rutas WHERE id = :id")
    suspend fun obtenerRutaPorId(id: Int): Ruta?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRuta(ruta: Ruta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRutas(rutas: List<Ruta>)

    @Update
    suspend fun actualizarRuta(ruta: Ruta)

    @Delete
    suspend fun eliminarRuta(ruta: Ruta)

    @Query("SELECT COUNT(*) FROM rutas")
    suspend fun contarRutas(): Int
}