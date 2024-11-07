package org.kamilimu.viazilink.client.presentation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

class NavigationHandler(private val navController: NavController) {

    fun navigateToClientHome() {
        navController.navigate("client_home")
    }

    fun navigateToClientOrders() {
        navController.navigate("client_orders")
    }

    // Add more navigation methods for client screens as needed
}
