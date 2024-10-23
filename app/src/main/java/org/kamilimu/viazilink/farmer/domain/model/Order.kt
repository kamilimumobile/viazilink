package org.kamilimu.viazilink.farmer.domain.model

/**
 * An order placed by a customer on an existing listing
 *
 * @param id Order id
 * @param customerId Id of the customer who has placed the order
 * @param orderTime A timestamp of when the order was placed
 * @param quantity Number of kilograms of potatoes of the order placed
 * @param typeOfPotato The specific type of potato on the order placed
 * @param imageUrl An image of the potatoes in the order placed
 * @param price Advertised cost of the potatoes in the listing
 */
data class Order(
    val id: String,
    val customerId: String,
    val orderTime: String,
    val quantity: Int,
    val typeOfPotato: String,
    val imageUrl: String,
    val price: Int
)
