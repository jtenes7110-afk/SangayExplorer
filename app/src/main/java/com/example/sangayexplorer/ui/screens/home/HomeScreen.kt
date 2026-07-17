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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {

    val rutas = viewModel.rutas

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

                    Text(
                        text = ruta.nombre,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(ruta.ubicacion)

                    Text(ruta.duracion)

                    Text(ruta.dificultad)

                }

            }

        }

    }

}