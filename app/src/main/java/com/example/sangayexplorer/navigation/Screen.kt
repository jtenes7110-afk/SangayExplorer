package com.example.sangayexplorer.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Favorites : Screen("favorites")
    data object Location : Screen("location")
    data object Settings : Screen("settings")
    data object RouteManagement : Screen("route_management")
    data object AddRoute : Screen("add_route")
    data object Edit : Screen("edit_route/{rutaId}") {

        fun createRoute(rutaId: Int): String {
            return "edit_route/$rutaId"
        }

    }
    data object Detail : Screen("detail/{rutaId}") {



        fun createRoute(rutaId: Int): String {
            return "detail/$rutaId"
        }

    }
}
