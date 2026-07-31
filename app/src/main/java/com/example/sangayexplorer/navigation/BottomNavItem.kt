package com.example.sangayexplorer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Inicio",
        route = Screen.Home.route,
        icon = Icons.Default.Home
    ),
    BottomNavItem(
        title = "Rutas",
        route = Screen.Location.route,
        icon = Icons.Default.LocationOn
    ),
    BottomNavItem(
        title = "Clima",
        route = Screen.Information.route,
        icon= Icons.Default.Cloud
    ),
    BottomNavItem(
        title = "Configuración",
        route = Screen.Settings.route,
        icon = Icons.Default.Settings
    )
)