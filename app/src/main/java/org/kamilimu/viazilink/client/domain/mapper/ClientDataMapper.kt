package org.kamilimu.viazilink.client.domain.mapper

import org.kamilimu.viazilink.client.domain.model.ClientProfile
import org.kamilimu.viazilink.farmer.domain.model.FarmerProfile

object ClientDataMapper {
    fun mapFarmerToClientProfile(farmerProfile: FarmerProfile): ClientProfile {
        // Converts FarmerProfile to ClientProfile
        return ClientProfile(
            id = farmerProfile.uid,
            name = farmerProfile.name,
            contactInfo = farmerProfile.email // Assuming FarmerProfile has an email property
        )
    }
}
