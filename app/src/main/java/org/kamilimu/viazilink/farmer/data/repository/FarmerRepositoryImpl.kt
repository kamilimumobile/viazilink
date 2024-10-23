package org.kamilimu.viazilink.farmer.data.repository

import arrow.core.Either
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.kamilimu.viazilink.farmer.domain.mapper.toFirestoreError
import org.kamilimu.viazilink.farmer.domain.model.FarmerProfile
import org.kamilimu.viazilink.farmer.domain.model.FirestoreError
import org.kamilimu.viazilink.farmer.domain.model.Listing
import org.kamilimu.viazilink.farmer.domain.model.Order
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository
import javax.inject.Inject

class FarmerRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    auth: FirebaseAuth
) : FarmerRepository {
    private val farmerId = auth.currentUser?.uid

    override suspend fun getFarmerListings(): Either<FirestoreError, List<Listing>> {
        return Either.catch {
            if (farmerId == null) throw Exception("User not Authenticated")
            val listings = db.collection("farmers")
                .document(farmerId)
                .collection("listings")
                .get()
                .await()
            if (listings.isEmpty) throw NullPointerException("No Document Found")
            listings.toObjects(Listing::class.java)
        }.mapLeft { throwable ->
            throwable.toFirestoreError()
        }
    }

    override suspend fun getFarmerProfile(): Either<FirestoreError, FarmerProfile> {
        return Either.catch {
            if (farmerId == null) throw Exception("User not Authenticated")
            val profile = db.collection("farmers")
                .document(farmerId)
                .collection("profile")
                .get()
                .await()
            profile.toObjects(FarmerProfile::class.java).first()
        }.mapLeft { throwable ->
            throwable.toFirestoreError()
        }
    }

    override suspend fun getFarmerOrders(): Either<FirestoreError, List<Order>> {
        return Either.catch {
            if (farmerId == null) throw Exception("User not Authenticated")
            val orders = db.collection("farmers")
                .document(farmerId)
                .collection("orders")
                .get()
                .await()
            if (orders.isEmpty) throw NullPointerException("No Document Found")
            orders.toObjects(Order::class.java)
        }.mapLeft { throwable ->
            throwable.toFirestoreError()
        }
    }

    override suspend fun addListing(listing: Listing): Either<FirestoreError, Listing> {
        return Either.catch {
            if (farmerId == null) throw Exception("User not Authenticated")
            db.collection("farmers")
                .document(farmerId)
                .collection("listings")
                .add(listing)
                .await()
            listing
        }.mapLeft { throwable ->
            throwable.toFirestoreError()
        }
    }
}