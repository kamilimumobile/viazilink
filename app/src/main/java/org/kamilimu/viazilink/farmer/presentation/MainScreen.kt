package org.kamilimu.viazilink.farmer.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.presentation.components.BottomBar
import org.kamilimu.viazilink.farmer.presentation.components.NavBarItem
import org.kamilimu.viazilink.farmer.presentation.components.TopBar
import org.kamilimu.viazilink.util.AuthStatus
import org.kamilimu.viazilink.util.ScreenNames

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController: NavHostController = rememberNavController()
    val authStatus by authViewModel.authStatus.collectAsStateWithLifecycle()

    MainScreenContent(
        modifier = modifier,
        navController = navController,
        authStatus = authStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authStatus: AuthStatus
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenNames.entries
        .find { it.route == backStackEntry?.destination?.route } ?: ScreenNames.HomeScreen

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                currentScreen = currentScreen,
                onBackClicked = {
                    navController.navigateUp()
                },
                scrollBehaviour = scrollBehavior
            )
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier.fillMaxWidth(),
                navBarItems = listOf(
                    NavBarItem(
                        label = R.string.home,
                        screenName = ScreenNames.HomeScreen,
                        filledIcon = Icons.Filled.Home,
                        outlinedIcon = Icons.Outlined.Home
                    ),
                    NavBarItem(
                        label = R.string.listings,
                        screenName = ScreenNames.ExistingListingsScreen,
                        filledIcon = Icons.AutoMirrored.Filled.LibraryBooks,
                        outlinedIcon = Icons.AutoMirrored.Outlined.LibraryBooks
                    ),
                    NavBarItem(
                        label = R.string.new_listing,
                        screenName = ScreenNames.NewListingScreen,
                        filledIcon = Icons.Filled.Add,
                        outlinedIcon = Icons.Outlined.Add
                    ),
                    NavBarItem(
                        label = R.string.profile,
                        screenName = ScreenNames.ProfileScreen,
                        filledIcon = Icons.Filled.Person,
                        outlinedIcon = Icons.Outlined.Person
                    )
                ),
                currentScreen = currentScreen
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if (authStatus == AuthStatus.Authenticated) {
                "farmerModule"
            } else {
                "authScreens"
            },
            modifier = Modifier.padding(paddingValues)
        ) {
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
                            navController.navigate(route = "farmerModule") {
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
                startDestination = ScreenNames.HomeScreen.route,
                route = "farmerModule"
            ) {
                composable(route = ScreenNames.HomeScreen.route) {
                    HomePage(scrollBehavior = scrollBehavior)
                }

                composable(route = ScreenNames.NewListingScreen.route) {}

                composable(route = ScreenNames.ProfileScreen.route) {}

                composable(route = ScreenNames.ExistingListingsScreen.route) {}
            }
        }
    }
}