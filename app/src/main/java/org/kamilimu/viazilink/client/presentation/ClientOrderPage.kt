package org.kamilimu.viazilink.client.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.kamilimu.viazilink.client.presentation.components.OrderCard
import org.kamilimu.viazilink.client.presentation.viewmodel.ClientOrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientOrderPage(
    clientOrderViewModel: ClientOrderViewModel = viewModel(),
    onOrderClick: (String) -> Unit
) {
    val orders = clientOrderViewModel.orders.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text("Client Orders") }) },
        content = { innerPadding -> // Use the innerPadding parameter
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Apply the padding to the Column
            ) {
                orders.value.forEach { order ->
                    OrderCard(order = order) {
                        onOrderClick(order.orderId)
                    }
                }
            }
        }
    )
}
