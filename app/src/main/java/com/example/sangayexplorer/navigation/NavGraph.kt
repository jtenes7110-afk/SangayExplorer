package com.example.sangayexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sangayexplorer.ui.screens.detail.DetailScreen
import com.example.sangayexplorer.ui.screens.favorites.FavoritesScreen
import com.example.sangayexplorer.ui.screens.home.HomeScreen
import com.example.sangayexplorer.ui.screens.location.LocationScreen
import com.example.sangayexplorer.ui.screens.settings.SettingsScreen

@Composable
fun SangayNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }

        composable(Screen.Location.route) {
            LocationScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen()
        }

        composable(Screen.Detail.route) {
            DetailScreen()
        }
    }
}