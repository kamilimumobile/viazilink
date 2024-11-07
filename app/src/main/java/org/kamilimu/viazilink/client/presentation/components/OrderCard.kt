package org.kamilimu.viazilink.client.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.kamilimu.viazilink.client.domain.model.ClientOrder

@Composable
fun OrderCard(
    order: ClientOrder,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Use CardDefaults.cardElevation for Material 3
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Order ID: ${order.orderId}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Product: ${order.listingId}")
            Text(text = "Quantity: ${order.quantity}")
            Text(text = "Total Price: ${order.price}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onClick) {
                Text("View Order")
            }
        }
    }
}
