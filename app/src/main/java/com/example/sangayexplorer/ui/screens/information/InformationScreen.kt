package com.example.sangayexplorer.ui.screens.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.data.repository.WeatherRepository
import com.example.sangayexplorer.viewmodel.WeatherViewModel
import com.example.sangayexplorer.viewmodel.WeatherViewModelFactory

private fun obtenerEstadoClima(codigo: Int): String {
    return when (codigo) {
        0 -> "☀️ Soleado"
        1 -> "🌤 Mayormente despejado"
        2 -> "⛅ Parcialmente nublado"
        3 -> "☁️ Nublado"

        45, 48 -> "🌫 Niebla"

        51, 53, 55 -> "🌦 Llovizna"

        61, 63, 65 -> "🌧 Lluvia"

        71, 73, 75 -> "❄️ Nieve"

        95 -> "⛈ Tormenta"

        else -> "No disponible"
    }
}

@Composable
fun InformationScreen() {

    val repository = remember {
        WeatherRepository()
    }

    val weatherViewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(repository)
    )

    val weather by weatherViewModel.weather.collectAsState()
    val loading by weatherViewModel.loading.collectAsState()
    val error by weatherViewModel.error.collectAsState()

    LaunchedEffect(Unit) {

        weatherViewModel.obtenerClima()

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "Centro de Información",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {

            loading -> {

                CircularProgressIndicator()

                Text("Consultando clima...")

            }

            error != null -> {

                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error
                )

            }

            weather != null -> {

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )

                ) {

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),

                        horizontalAlignment = Alignment.CenterHorizontally,

                        verticalArrangement = Arrangement.spacedBy(16.dp)

                    ) {
                        Text(

                            text = "🌤",

                            style = MaterialTheme.typography.displayMedium

                        )

                        Text(
                            text = "Parque Nacional Sangay",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(

                            text = "Centro de Información",

                            style = MaterialTheme.typography.bodyMedium

                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(

                            text = "🌡 Temperatura",
                            style = MaterialTheme.typography.titleMedium

                        )

                        Text(

                            text = "${weather!!.current.temperatura} °C",
                            style = MaterialTheme.typography.headlineSmall

                        )

                        Text(
                            text = "💨 Velocidad del viento",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "${weather!!.current.velocidadViento} km/h",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            text = "💧 Humedad",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "${weather!!.current.humedad} %",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            text = "🌤 Estado del clima",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = obtenerEstadoClima(weather!!.current.codigoClima),
                            style = MaterialTheme.typography.headlineSmall
                        )

                    }

                }

            }

        }

    }
}