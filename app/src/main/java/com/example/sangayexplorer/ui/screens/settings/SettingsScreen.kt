package com.example.sangayexplorer.ui.screens.settings

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sangayexplorer.navigation.Screen

import androidx.compose.material3.Switch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.viewmodel.ThemeViewModel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth


@Composable
fun SettingsScreen(
    navController: NavController
) {
    val themeViewModel: ThemeViewModel = viewModel()

    val darkMode by themeViewModel.darkMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)


    ) {

        Text(
            text = "Configuración",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = {
                navController.navigate(Screen.RouteManagement.route)
            }
        ) {

            Text("Administrar rutas")

        }

        Text(
            text = "Sangay Explorer\nVersión 1.0",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Modo oscuro"
            )

            Switch(

                checked = darkMode,

                onCheckedChange = {

                    themeViewModel.cambiarModoOscuro(it)

                }

            )

        }

    }

}