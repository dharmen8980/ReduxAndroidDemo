package com.teknophase.reduxexample.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teknophase.reduxexample.ui.screens.HomeScreen
import com.teknophase.reduxexample.ui.screens.LoginScreen
import com.teknophase.reduxexample.ui.screens.MapScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    Scaffold() { innerPadding ->

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = AppNavRoutes.MAP.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(
                route = AppNavRoutes.LOGIN.name
            ) {
                LoginScreen(navController = navController)
            }

            composable(route = AppNavRoutes.HOME.name) {
                HomeScreen()
            }

            composable(route = AppNavRoutes.MAP.name) {
                MapScreen()
            }

        }
    }
}