package com.example.sangayexplorer.ui.screens.location

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.core.content.ContextCompat

import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.sangayexplorer.data.repository.LocationRepository
import com.example.sangayexplorer.viewmodel.LocationViewModel
import com.example.sangayexplorer.viewmodel.LocationViewModelFactory
import com.example.sangayexplorer.ui.components.OpenMapsButton

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

@Composable
fun LocationScreen() {

    val context = LocalContext.current

    val repository = remember {
        LocationRepository(context)
    }

    val locationViewModel: LocationViewModel = viewModel(
        factory = LocationViewModelFactory(repository)
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->

        if (granted) {

            locationViewModel.obtenerUbicacion()

        }

    }

    val latitud by locationViewModel.latitud.collectAsState()
    val longitud by locationViewModel.longitud.collectAsState()
    val distancia by locationViewModel.distancia.collectAsState()
    val loading by locationViewModel.loading.collectAsState()
    val error by locationViewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Mi ubicación",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        when {

            loading -> {

                CircularProgressIndicator()

            }

            error != null -> {

                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error
                )

            }

            latitud != null && longitud != null -> {

                Card {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text("📍 Latitud: %.6f".format(latitud))

                        Text("📍 Longitud: %.6f".format(longitud))

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            "🚗 Distancia al Parque Nacional Sangay:"
                        )

                        Text(
                            "%.2f km".format(distancia)
                        )

                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                if (

                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                ) {

                    locationViewModel.obtenerUbicacion()

                } else {

                    launcher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )

                }

            }

        ) {

            Text("Actualizar ubicación")

        }

        Spacer(modifier = Modifier.height(16.dp))

        OpenMapsButton()

    }

}