package org.kamilimu.viazilink.farmer.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.kamilimu.viazilink.util.ScreenNames

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    MainScreenContent(
        modifier = modifier,
        navController = navController
    )
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenNames.LoginScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = ScreenNames.LoginScreen.route) {
                LoginScreen(navController = navController)
            }

            composable(route = ScreenNames.SignUpScreen.route) {
                SignUpScreen(navController = navController)
            }

            composable(route = ScreenNames.HomeScreen.route) {
            }
        }
    }
}