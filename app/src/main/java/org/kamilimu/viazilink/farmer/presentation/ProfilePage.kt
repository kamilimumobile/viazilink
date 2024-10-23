package org.kamilimu.viazilink.farmer.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Farmer profile details will be displayed here")
        Button(
            onClick = {
                authViewModel.onLogout()
                navController.navigate("authScreens")
                Toast.makeText(
                    context,
                    "Successfully Logged Out",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            Text("Logout")
        }
    }
}