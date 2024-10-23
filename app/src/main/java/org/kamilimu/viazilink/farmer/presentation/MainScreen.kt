package org.kamilimu.viazilink.farmer.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.kamilimu.viazilink.R
import org.kamilimu.viazilink.farmer.presentation.components.BottomBar
import org.kamilimu.viazilink.farmer.presentation.components.NavBarItem
import org.kamilimu.viazilink.farmer.presentation.components.TopBar
import org.kamilimu.viazilink.ui.theme.AppTheme
import org.kamilimu.viazilink.util.ScreenNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenNames.entries
        .find { it.route == backStackEntry?.destination?.route } ?: ScreenNames.HomeScreen

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
                currentScreen = currentScreen,
                onItemSelected = { screenName ->
                    navController.navigate(screenName.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ScreenNames.HomeScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = ScreenNames.HomeScreen.route) {
                HomePage(scrollBehavior = scrollBehavior)
            }
            composable(route = ScreenNames.NewListingScreen.route) {
                AddListingPage()
            }
            composable(route = ScreenNames.ProfileScreen.route) {
                ProfilePage()
            }
            composable(route = ScreenNames.ExistingListingsScreen.route) {
                ProduceListingsPage(scrollBehavior = scrollBehavior)
            }
        }
    }
}

@Preview(name = "MainScreenLight", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "MainScreenDark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            navController = rememberNavController()
        )
    }
}