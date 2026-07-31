package com.example.sangayexplorer.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.SangayExplorerApp
import com.example.sangayexplorer.data.repository.RutaRepository
import com.example.sangayexplorer.viewmodel.DetailViewModel
import com.example.sangayexplorer.viewmodel.DetailViewModelFactory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.navigation.NavController
import com.example.sangayexplorer.navigation.Screen

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun DetailScreen(
navController: NavController,
rutaId: Int
) {

    val context = LocalContext.current
    val app = context.applicationContext as SangayExplorerApp

    val factory = DetailViewModelFactory(
        RutaRepository(app.database.rutaDao())
    )

    val detailViewModel: DetailViewModel = viewModel(
        factory = factory
    )

    LaunchedEffect(rutaId) {
        detailViewModel.cargarRuta(rutaId)
    }

    val ruta by detailViewModel.ruta.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        if (ruta == null) {

            Text(
                text = "Cargando información...",
                style = MaterialTheme.typography.titleLarge
            )

        } else {

            Image(
                painter = painterResource(id = ruta!!.imagen),
                contentDescription = ruta!!.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = ruta!!.nombre,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "📍 ${ruta!!.ubicacion}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "⏱ Duración: ${ruta!!.duracion}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🥾 Dificultad: ${ruta!!.dificultad}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = ruta!!.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(

                onClick = {

                    navController.navigate(
                        Screen.Edit.createRoute(ruta!!.id)
                    )

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("✏ Editar ruta")

            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(

                onClick = {

                    navController.popBackStack()

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("← Volver")

            }

        }

    }

}