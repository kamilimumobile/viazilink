package org.kamilimu.viazilink.farmer.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository
import javax.inject.Inject

class FarmerRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : FarmerRepository {
}