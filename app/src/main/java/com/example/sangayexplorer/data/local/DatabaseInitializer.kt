package com.example.sangayexplorer.data.local

import com.example.sangayexplorer.R
import com.example.sangayexplorer.data.model.Ruta
import com.example.sangayexplorer.data.repository.RutaRepository

object DatabaseInitializer {

    suspend fun initialize(repository: RutaRepository) {

        if (repository.contarRutas() == 0) {

            repository.insertarRutas(

                listOf(

                    Ruta(
                        id = 1,
                        nombre = "Laguna de Atillo",
                        descripcion = "Complejo lacustre conformado por las lagunas Kuyuk, La Magdalena, Colay, Chapanapungo y Shisnian. Es uno de los principales atractivos turísticos del Parque Nacional Sangay.",
                        ubicacion = "Provincia de Chimborazo",
                        duracion = "4 - 6 horas",
                        dificultad = "Media",
                        imagen = R.drawable.atillo
                    ),

                    Ruta(
                        id = 2,
                        nombre = "Complejo Lacustre Ozogoche",
                        descripcion = "Conformado por las lagunas Cubillín, Magtayán, Pichahuiña, Verdecocha, Boazo, Yanacocha y Tinguicocha, destacándose por su belleza paisajística.",
                        ubicacion = "Provincia de Chimborazo",
                        duracion = "5 horas",
                        dificultad = "Media",
                        imagen = R.drawable.ozogoche
                    ),

                    Ruta(
                        id = 3,
                        nombre = "Volcán El Altar",
                        descripcion = "Volcán extinto compuesto por dieciséis lagunas, entre ellas Amarilla, Azul, Mandur, Pintada y Estrellada. Es uno de los destinos de alta montaña más representativos del parque.",
                        ubicacion = "Provincia de Chimborazo",
                        duracion = "1 día",
                        dificultad = "Alta",
                        imagen = R.drawable.altar
                    )

                )

            )

        }

    }

}