package com.example.sangayexplorer.network

import com.example.sangayexplorer.network.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/forecast")
    suspend fun obtenerClima(

        @Query("latitude")
        latitude: Double,

        @Query("longitude")
        longitude: Double,

        @Query("current")
        current: String =
            "temperature_2m,wind_speed_10m,relative_humidity_2m,weather_code"

    ): WeatherResponse

}