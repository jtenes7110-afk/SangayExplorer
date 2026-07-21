package com.example.sangayexplorer.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Favorites : Screen("favorites")
    data object Location : Screen("location")
    data object Settings : Screen("settings")
    data object Detail : Screen("detail/{rutaId}") {

        fun createRoute(rutaId: Int): String {
            return "detail/$rutaId"
        }

    }
}