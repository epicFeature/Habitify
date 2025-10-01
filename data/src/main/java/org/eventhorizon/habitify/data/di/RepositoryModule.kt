package org.eventhorizon.habitify.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.eventhorizon.habitify.data.repositories.database.HabitRepositoryImpl
import org.eventhorizon.habitify.data.repositories.datastore.OnboardingRepositoryImpl
import org.eventhorizon.habitify.data.repositories.network.QuoteApiRepositoryImpl
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import org.eventhorizon.habitify.domain.repository.datastore.OnboardingRepository
import org.eventhorizon.habitify.domain.repository.network.QuoteApiRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHabitRepository(
        habitRepositoryImpl: HabitRepositoryImpl
    ): HabitRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Binds
    @Singleton
    abstract fun bindQuoteRepository(
        quoteRepositoryImpl: QuoteApiRepositoryImpl
    ): QuoteApiRepository
}