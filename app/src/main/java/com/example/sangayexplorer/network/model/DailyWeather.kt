package com.example.sangayexplorer.network.model

import com.google.gson.annotations.SerializedName

data class DailyWeather(

    @SerializedName("precipitation_probability_max")
    val probabilidadLluvia: List<Int>

)