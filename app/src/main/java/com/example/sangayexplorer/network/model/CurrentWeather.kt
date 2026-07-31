package com.example.sangayexplorer.network.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(

    @SerializedName("temperature_2m")
    val temperatura: Double,

    @SerializedName("wind_speed_10m")
    val velocidadViento: Double

)