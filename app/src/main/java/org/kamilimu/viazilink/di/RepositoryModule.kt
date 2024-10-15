package org.kamilimu.viazilink.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.kamilimu.viazilink.farmer.data.repository.AuthRepositoryImpl
import org.kamilimu.viazilink.farmer.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Provides the [AuthRepository] where it needs to be injected
     */
    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}