package com.example.sangayexplorer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rutas")
data class Ruta(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,

    val descripcion: String,

    val ubicacion: String,

    val duracion: String,

    val dificultad: String,

    val imagen: Int

)
