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

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.sangayexplorer.ui.screens.add.AddRouteScreen
import com.example.sangayexplorer.ui.screens.management.RouteManagementScreen

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

        composable(Screen.RouteManagement.route) {

            RouteManagementScreen(
                navController = navController
            )

        }

        composable(Screen.AddRoute.route) {
            AddRouteScreen(
                navController = navController
            )
        }

        composable(Screen.Home.route) {

            val context = LocalContext.current
            val app = context.applicationContext as SangayExplorerApp

            val factory = HomeViewModelFactory(
                RutaRepository(app.database.rutaDao())
            )

            val homeViewModel: HomeViewModel = viewModel(
                factory = factory
            )

            HomeScreen(
                navController = navController,
                viewModel = homeViewModel
            )
        }

        composable(Screen.Location.route) {
            LocationScreen()
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("rutaId") {
                    type = NavType.IntType
                }
            )
        ) {

            val rutaId = it.arguments?.getInt("rutaId") ?: 0

            DetailScreen(
                navController = navController,
                rutaId = rutaId
            )
        }
    }
}