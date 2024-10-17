package org.kamilimu.viazilink.farmer.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.kamilimu.viazilink.farmer.presentation.components.StartScreen
import org.kamilimu.viazilink.util.AuthStatus
import org.kamilimu.viazilink.util.ScreenNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavigationHandler(
    navController: NavHostController,
    authStatus: AuthStatus
) {
    NavHost(
        navController = navController,
        startDestination = "startScreen"
    ) {
        composable(route = "startScreen") {
            StartScreen(
                authStatus = authStatus,
                toFarmerScreens = {
                    navController.navigate(route = "farmerScreens") {
                        popUpTo("startScreen") {
                            inclusive = true
                        }
                    }
                },
                toAuthScreens = {
                    navController.navigate(route = "authScreens") {
                        popUpTo("startScreen") {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        /**
         * Nested nav graph for Auth screens
         */
        navigation(
            startDestination = ScreenNames.LoginScreen.route,
            route = "authScreens"
        ) {
            composable(route = ScreenNames.LoginScreen.route) {
                LoginScreen(
                    navController = navController,
                    onSuccessfulLogin = {
                        navController.navigate(route = "farmerScreens") {
                            // Pop all child destinations of `authScreens` from the backStack
                            popUpTo("authScreens") {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(route = ScreenNames.SignUpScreen.route) {
                SignUpScreen(navController = navController)
            }
        }

        /**
         * Nested nav graph for farmer screens once a user logs in successfully
         */
        navigation(
            startDestination = ScreenNames.MainScreen.route,
            route = "farmerScreens"
        ) {
            composable(route = ScreenNames.MainScreen.route) {
                MainScreen(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}