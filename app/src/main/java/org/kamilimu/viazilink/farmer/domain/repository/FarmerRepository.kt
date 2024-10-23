package org.kamilimu.viazilink.farmer.domain.repository

import arrow.core.Either
import org.kamilimu.viazilink.farmer.domain.model.FarmerProfile
import org.kamilimu.viazilink.farmer.domain.model.FirestoreError
import org.kamilimu.viazilink.farmer.domain.model.Listing
import org.kamilimu.viazilink.farmer.domain.model.Order

interface FarmerRepository {
    suspend fun getFarmerListings(): Either<FirestoreError, List<Listing>>

    suspend fun getFarmerProfile(): Either<FirestoreError, FarmerProfile>

    suspend fun getFarmerOrders(): Either<FirestoreError, List<Order>>

    suspend fun addListing(listing: Listing): Either<FirestoreError, Listing>
}