package com.example.sangayexplorer.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.viewmodel.HomeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {

    val rutas by viewModel.rutas.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(rutas) { ruta ->

            Card(
                modifier = Modifier
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Image(
                        painter = painterResource(id = ruta.imagen),
                        contentDescription = ruta.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = ruta.nombre,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = 12.dp)
                    )

                    Text(
                        text = "📍 ${ruta.ubicacion}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "⏱ Duración: ${ruta.duracion}"
                    )

                    Text(
                        text = "🥾 Dificultad: ${ruta.dificultad}"
                    )

                    Text(
                        text = ruta.descripcion,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                }

            }

        }

    }

}