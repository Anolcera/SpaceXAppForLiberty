package com.plcoding.stockmarketapp.di

import com.whichonespink.spacex.data.repository.SpaceXRepositoryImpl
import com.whichonespink.spacex.domain.repository.SpaceXRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSpaceXRepository(
        spaceXRepositoryImpl: SpaceXRepositoryImpl
    ): SpaceXRepository
}