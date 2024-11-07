package org.kamilimu.viazilink.client.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.kamilimu.viazilink.client.presentation.components.ClientBottomBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf("client_home") } // Example selected tab state

    Scaffold(
        bottomBar = {
            ClientBottomBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    navController.navigate(it) // Navigate to the selected tab
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "client_home",
            modifier = Modifier.padding(innerPadding) // Apply the padding to avoid overlapping content
        ) {
            composable("client_home") { ClientHomePage(onItemClick = { /* Handle item click */ }) }
            composable("client_orders") { ClientOrderPage(onOrderClick = { /* Handle order click */ }) }
            // Add more screens here
        }
    }
}
