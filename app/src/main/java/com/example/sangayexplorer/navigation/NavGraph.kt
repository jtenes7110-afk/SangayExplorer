package com.example.sangayexplorer.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sangayexplorer.ui.screens.detail.DetailScreen
import com.example.sangayexplorer.ui.screens.favorites.FavoritesScreen
import com.example.sangayexplorer.ui.screens.home.HomeScreen
import com.example.sangayexplorer.ui.screens.location.LocationScreen
import com.example.sangayexplorer.ui.screens.settings.SettingsScreen

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.SangayExplorerApp
import com.example.sangayexplorer.data.repository.RutaRepository
import com.example.sangayexplorer.viewmodel.HomeViewModel
import com.example.sangayexplorer.viewmodel.HomeViewModelFactory

@Composable
fun SangayNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(Screen.Home.route) {

            val context = LocalContext.current
            val app = context.applicationContext as SangayExplorerApp

            val repository = RutaRepository(app.database.rutaDao())

            val factory = HomeViewModelFactory(repository)

            val viewModel: HomeViewModel = viewModel(
                factory = factory
            )

            HomeScreen(
                viewModel = viewModel
            )
        }

        composable(Screen.Location.route) {
            LocationScreen()
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen()
        }

        composable(Screen.Detail.route) {
            DetailScreen()
        }
    }
}