package com.example.sangayexplorer.ui.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sangayexplorer.SangayExplorerApp
import com.example.sangayexplorer.data.repository.ImageRepository
import com.example.sangayexplorer.data.repository.RutaRepository
import com.example.sangayexplorer.ui.components.RouteForm
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
    var imagenSeleccionada by remember {

        mutableStateOf(ImageRepository.imagenes.first())

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Agregar nueva ruta",
            style = MaterialTheme.typography.headlineMedium
        )

        RouteForm(

            nombre = nombre,
            onNombreChange = {
                nombre = it
            },

            ubicacion = ubicacion,
            onUbicacionChange = {
                ubicacion = it
            },

            duracion = duracion,
            onDuracionChange = {
                duracion = it
            },

            dificultad = dificultad,
            onDificultadChange = {
                dificultad = it
            },

            descripcion = descripcion,
            onDescripcionChange = {
                descripcion = it
            },

            imagenSeleccionada = imagenSeleccionada,

            onImagenSeleccionada = { imagen ->

                imagenSeleccionada = imagen

                viewModel.seleccionarImagen(
                    imagen.imagen
                )

            }

        )

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