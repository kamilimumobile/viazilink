package org.kamilimu.viazilink.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.kamilimu.viazilink.farmer.data.repository.AuthRepositoryImpl
import org.kamilimu.viazilink.farmer.data.repository.FarmerRepositoryImpl
import org.kamilimu.viazilink.farmer.domain.repository.AuthRepository
import org.kamilimu.viazilink.farmer.domain.repository.FarmerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Provides the [AuthRepository] where it is a dependency
     */
    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    /**
     * Provides the [FarmerRepository] where it is a dependency
     */
    @Binds
    @Singleton
    abstract fun provideFarmerRepository(
        farmerRepositoryImpl: FarmerRepositoryImpl
    ): FarmerRepository
}