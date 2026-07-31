package com.example.sangayexplorer.data.model

import androidx.annotation.DrawableRes

data class ImagenRuta(

    val id: Int,

    val nombre: String,

    @DrawableRes
    val imagen: Int

)