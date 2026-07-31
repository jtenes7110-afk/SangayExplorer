package com.example.sangayexplorer.ui.screens.edit

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.SangayExplorerApp
import com.example.sangayexplorer.data.repository.RutaRepository
import com.example.sangayexplorer.viewmodel.EditRouteViewModel
import com.example.sangayexplorer.viewmodel.EditRouteViewModelFactory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.sangayexplorer.ui.components.RouteForm

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.AlertDialog

@Composable
fun EditRouteScreen(
    navController: NavHostController,
    rutaId: Int
) {

    val context = LocalContext.current

    val app = context.applicationContext as SangayExplorerApp

    val factory = EditRouteViewModelFactory(
        RutaRepository(app.database.rutaDao())
    )

    val viewModel: EditRouteViewModel = viewModel(
        factory = factory
    )

    LaunchedEffect(Unit) {
        viewModel.cargarRuta(rutaId)
    }

    val ruta by viewModel.ruta.collectAsState()
    var nombre by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var dificultad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var mostrarDialogoEliminar by remember {
        mutableStateOf(false)
    }
    var imagenSeleccionada by remember {
        mutableStateOf(
            com.example.sangayexplorer.data.repository.ImageRepository.imagenes.first()
        )
    }

    LaunchedEffect(ruta) {

        ruta?.let {

            nombre = it.nombre
            ubicacion = it.ubicacion
            duracion = it.duracion
            dificultad = it.dificultad
            descripcion = it.descripcion

            viewModel.seleccionarImagen(it.imagen)

            imagenSeleccionada =
                com.example.sangayexplorer.data.repository.ImageRepository.imagenes
                    .firstOrNull { imagen ->

                        imagen.imagen == it.imagen

                    } ?: com.example.sangayexplorer.data.repository.ImageRepository.imagenes.first()

        }

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)

    ) {

        Text(
            text = "Editar ruta",
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

                viewModel.actualizarRuta(

                    nombre = nombre,
                    ubicacion = ubicacion,
                    duracion = duracion,
                    dificultad = dificultad,
                    descripcion = descripcion

                )

                navController.popBackStack()

            }

        ) {

            Text("Guardar cambios")

        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(

            onClick = {

                mostrarDialogoEliminar = true

            },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )

        ) {

            Text("🗑 Eliminar ruta")

        }

        if (mostrarDialogoEliminar) {

            AlertDialog(

                onDismissRequest = {

                    mostrarDialogoEliminar = false

                },

                title = {

                    Text("Eliminar ruta")

                },

                text = {

                    Text(
                        "¿Está seguro de eliminar esta ruta? Esta acción no se puede deshacer."
                    )

                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            viewModel.eliminarRuta()

                            mostrarDialogoEliminar = false

                            navController.navigate("home") {

                                popUpTo(0)

                            }

                        }

                    ) {

                        Text("Eliminar")

                    }

                },

                dismissButton = {

                    TextButton(

                        onClick = {

                            mostrarDialogoEliminar = false

                        }

                    ) {

                        Text("Cancelar")

                    }

                }

            )

        }
    }
}
