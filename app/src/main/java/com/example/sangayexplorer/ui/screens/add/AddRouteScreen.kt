package com.example.sangayexplorer.ui.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sangayexplorer.SangayExplorerApp
import com.example.sangayexplorer.data.repository.RutaRepository
import com.example.sangayexplorer.viewmodel.AddRouteViewModel
import com.example.sangayexplorer.viewmodel.AddRouteViewModelFactory

@Composable
fun AddRouteScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    val app = context.applicationContext as SangayExplorerApp

    val factory = AddRouteViewModelFactory(
        RutaRepository(app.database.rutaDao())
    )

    val viewModel: AddRouteViewModel = viewModel(
        factory = factory
    )

    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var dificultad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Agregar nueva ruta",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicación") }
        )

        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duración") }
        )

        OutlinedTextField(
            value = dificultad,
            onValueChange = { dificultad = it },
            label = { Text("Dificultad") }
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") }
        )

        OutlinedButton(
            onClick = {
                // Próxima iteración
            }
        ) {
            Text("Seleccionar imagen")
        }

        Button(
            onClick = {

                if (
                    nombre.isNotBlank() &&
                    ubicacion.isNotBlank() &&
                    duracion.isNotBlank() &&
                    dificultad.isNotBlank() &&
                    descripcion.isNotBlank()
                ) {

                    viewModel.guardarRuta(
                        nombre = nombre,
                        ubicacion = ubicacion,
                        duracion = duracion,
                        dificultad = dificultad,
                        descripcion = descripcion
                    )

                    navController.popBackStack()

                }

            }
        ) {
            Text("Guardar")
        }
    }
}