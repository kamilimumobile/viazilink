package org.kamilimu.viazilink.client.domain.model

data class ClientOrder(
    val orderId: String,
    val clientId: String,
    val listingId: String,
    val quantity: Int,
    val price: Double
)
