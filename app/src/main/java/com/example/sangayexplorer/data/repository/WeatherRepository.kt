package com.example.sangayexplorer.data.repository

import com.example.sangayexplorer.network.RetrofitClient

class WeatherRepository {

    suspend fun obtenerClima(
        latitud: Double,
        longitud: Double
    ) =
        RetrofitClient.api.obtenerClima(
            latitude = latitud,
            longitude = longitud
        )

}