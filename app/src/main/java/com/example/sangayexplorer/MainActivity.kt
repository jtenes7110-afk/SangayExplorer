package com.example.sangayexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sangayexplorer.ui.theme.SangayExplorerTheme
import com.example.sangayexplorer.navigation.SangayNavGraph
import androidx.navigation.compose.rememberNavController
import com.example.sangayexplorer.ui.components.BottomBar

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sangayexplorer.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val themeViewModel: ThemeViewModel = viewModel()

            val darkMode by themeViewModel.darkMode.collectAsState()

            SangayExplorerTheme(
                darkTheme = darkMode
            ) {

                val navController = rememberNavController()

                Scaffold(

                    bottomBar = {

                        BottomBar(navController)

                    }

                ) { padding ->

                    SangayNavGraph(
                        navController = navController,
                        paddingValues = padding
                    )

                }

            }

        }
    }
}
