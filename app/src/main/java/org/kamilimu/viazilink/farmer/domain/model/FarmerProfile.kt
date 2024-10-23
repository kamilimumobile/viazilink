package org.kamilimu.viazilink.farmer.domain.model

/**
 * Farmer profile
 *
 * @param uid Unique identifier of the current user, retrieved from FirebaseAuth
 * @param name Full name of the farmer
 * @param location Current location of the farmer
 * @param email Email address of the farmer
 * @param phone Phone number of the farmer
 */
data class FarmerProfile(
    val uid: String,
    val name: String,
    val location: String,
    val email: String,
    val phone: String
)
