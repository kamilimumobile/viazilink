package org.kamilimu.viazilink.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.kamilimu.viazilink.client.domain.repository.AuthRepository // Ensure this is the correct import
import org.kamilimu.viazilink.client.data.repository.AuthRepositoryImpl // Ensure this matches the AuthRepository interface
import org.kamilimu.viazilink.farmer.data.repository.FarmerRepositoryImpl
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Provides the [AuthRepository] where it is a dependency.
     */
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl // Ensure this implementation matches the AuthRepository interface in the client package
    ): AuthRepository

    /**
     * Provides the [FarmerRepository] where it is a dependency.
     */
    @Binds
    abstract fun provideFarmerRepository(
        farmerRepositoryImpl: FarmerRepositoryImpl
    ): FarmerRepository
}
