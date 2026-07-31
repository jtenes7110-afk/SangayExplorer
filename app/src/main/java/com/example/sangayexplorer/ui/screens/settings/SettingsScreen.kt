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

@Composable
fun SettingsScreen(
    navController: NavController
) {

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

    }

}