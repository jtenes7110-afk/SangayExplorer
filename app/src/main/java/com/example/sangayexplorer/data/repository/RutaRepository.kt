package com.example.sangayexplorer.data.repository

import com.example.sangayexplorer.data.model.Ruta

class RutaRepository {

    fun obtenerRutas(): List<Ruta> {

        return listOf(

            Ruta(
                id = 1,
                nombre = "Laguna de Atillo",
                descripcion = "Hermosa laguna ubicada dentro del Parque Nacional Sangay.",
                ubicacion = "Chimborazo",
                duracion = "3 horas",
                dificultad = "Media",
                imagen = 0
            ),

            Ruta(
                id = 2,
                nombre = "Volcán El Altar",
                descripcion = "Uno de los volcanes más impresionantes del Ecuador.",
                ubicacion = "Chimborazo",
                duracion = "2 días",
                dificultad = "Alta",
                imagen = 0
            ),

            Ruta(
                id = 3,
                nombre = "Volcán Sangay",
                descripcion = "Volcán activo declarado Patrimonio Natural de la Humanidad.",
                ubicacion = "Morona Santiago",
                duracion = "2 días",
                dificultad = "Alta",
                imagen = 0
            )

        )
    }
}