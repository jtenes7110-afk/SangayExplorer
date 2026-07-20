package com.example.sangayexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sangayexplorer.data.model.Ruta

import kotlinx.coroutines.flow.Flow

@Dao
interface RutaDao {

    @Query("SELECT * FROM rutas")
    fun obtenerRutas(): Flow<List<Ruta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRutas(rutas: List<Ruta>)

}