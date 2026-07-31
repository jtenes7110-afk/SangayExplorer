package com.example.sangayexplorer.ui.screens.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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

                Card {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Parque Nacional Sangay",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Temperatura: ${weather!!.current.temperatura} °C"
                        )

                        Text(
                            text = "Viento: ${weather!!.current.velocidadViento} km/h"
                        )

                    }

                }

            }

        }

    }
}