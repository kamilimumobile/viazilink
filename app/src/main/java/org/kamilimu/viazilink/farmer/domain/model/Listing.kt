package org.kamilimu.viazilink.farmer.domain.model

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver

/**
 * An item listing created by a farmer
 *
 * @param type The type of potato being posted
 * @param quantity The quantity in kilograms
 * @param price The price in Ksh
 * @param deliveryTimeframe The maximum time it can take (in days) for the item to be delivered
 * @param imageUrl Image URI of the product being posted
 */
data class Listing(
    val type: String,
    val quantity: Int,
    val price: Int,
    val deliveryTimeframe: Int,
    val imageUrl: String? = null
)

val listingSaver = Saver<Listing, Map<String, Any>>(
    save = { listing ->
        mapOf(
            "type" to listing.type,
            "quantity" to listing.quantity,
            "price" to listing.price,
            "deliveryTimeframe" to listing.deliveryTimeframe,
            "imageUrl" to (listing.imageUrl ?: "")
        )
    },
    restore = { map ->
        Listing(
            type = map["type"] as String,
            quantity = map["quantity"] as Int,
            price = map["price"] as Int,
            deliveryTimeframe = map["deliveryTimeframe"] as Int,
            imageUrl = (map["imageUrl"] as String).takeIf { it.isNotEmpty() }
        )
    }
)
