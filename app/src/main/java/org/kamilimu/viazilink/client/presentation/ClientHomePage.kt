package org.kamilimu.viazilink.client.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.kamilimu.viazilink.client.presentation.components.ClientListingCard
import org.kamilimu.viazilink.client.presentation.viewmodel.ClientHomePageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientHomePage(
    clientHomePageViewModel: ClientHomePageViewModel = viewModel(),
    onItemClick: (String) -> Unit
) {
    val listings = clientHomePageViewModel.listings.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Client Home") }) },
        content = { paddingValues -> // Accept the paddingValues parameter
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Use the paddingValues here
            ) {
                listings.value.forEach { listing ->
                    ClientListingCard(listing = listing) {
                        onItemClick(listing.id)
                    }
                }
            }
        }
    )
}
