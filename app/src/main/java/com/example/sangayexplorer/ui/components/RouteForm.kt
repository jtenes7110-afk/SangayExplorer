package com.example.sangayexplorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sangayexplorer.data.model.ImagenRuta
import com.example.sangayexplorer.data.repository.ImageRepository

@Composable
fun RouteForm(

    nombre: String,
    onNombreChange: (String) -> Unit,

    ubicacion: String,
    onUbicacionChange: (String) -> Unit,

    duracion: String,
    onDuracionChange: (String) -> Unit,

    dificultad: String,
    onDificultadChange: (String) -> Unit,

    descripcion: String,
    onDescripcionChange: (String) -> Unit,

    imagenSeleccionada: ImagenRuta,
    onImagenSeleccionada: (ImagenRuta) -> Unit

) {

    var mostrarSelector by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = ubicacion,
            onValueChange = onUbicacionChange,
            label = { Text("Ubicación") }
        )

        OutlinedTextField(
            value = duracion,
            onValueChange = onDuracionChange,
            label = { Text("Duración") }
        )

        OutlinedTextField(
            value = dificultad,
            onValueChange = onDificultadChange,
            label = { Text("Dificultad") }
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripción") }
        )

        Image(
            painter = painterResource(imagenSeleccionada.imagen),
            contentDescription = imagenSeleccionada.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        OutlinedButton(
            onClick = {
                mostrarSelector = true
            }
        ) {
            Text("Cambiar imagen")
        }

        if (mostrarSelector) {

            AlertDialog(

                onDismissRequest = {
                    mostrarSelector = false
                },

                title = {
                    Text("Seleccione una imagen")
                },

                text = {

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(ImageRepository.imagenes) { imagen ->

                            Card(

                                modifier = Modifier
                                    .clickable {

                                        onImagenSeleccionada(imagen)

                                        mostrarSelector = false

                                    }

                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(8.dp)
                                ) {

                                    Image(
                                        painter = painterResource(imagen.imagen),
                                        contentDescription = imagen.nombre,
                                        modifier = Modifier.height(90.dp)
                                    )

                                    Text(imagen.nombre)

                                }

                            }

                        }

                    }

                },

                confirmButton = {

                    TextButton(
                        onClick = {
                            mostrarSelector = false
                        }
                    ) {
                        Text("Cerrar")
                    }

                }

            )

        }

    }

}