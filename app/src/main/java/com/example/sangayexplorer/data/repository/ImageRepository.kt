package com.example.sangayexplorer.data.repository

import com.example.sangayexplorer.R
import com.example.sangayexplorer.data.model.ImagenRuta

object ImageRepository {

    val imagenes = listOf(

        ImagenRuta(
            id = 1,
            nombre = "Altar",
            imagen = R.drawable.altar
        ),

        ImagenRuta(
            id =2,
            nombre = "Atillo",
            imagen = R.drawable.atillo
        ),

        ImagenRuta(
            id = 3,
            nombre = "Culebrilla",
            imagen = R.drawable.culebrilla
        ),

        ImagenRuta(
            id = 4,
            nombre = "Ozogoche",
            imagen = R.drawable.ozogoche
        ),

        ImagenRuta(
            id = 5,
            nombre = "Qhapaq Ñan",
            imagen = R.drawable.qhapaq
        ),

        ImagenRuta(
            id = 6,
            nombre = "Sardinayacu",
            imagen = R.drawable.sardinayacu
        ),

        ImagenRuta(
            id = 7,
            nombre = "Volcán Tungurahua",
            imagen = R.drawable.volcantungurahua
        )

    )

}