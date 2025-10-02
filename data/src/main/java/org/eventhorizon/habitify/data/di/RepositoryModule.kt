package org.eventhorizon.habitify.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.eventhorizon.habitify.data.repositories.database.HabitRepositoryImpl
import org.eventhorizon.habitify.data.repositories.datastore.OnboardingRepositoryImpl
import org.eventhorizon.habitify.data.repositories.network.QuoteApiRepositoryImpl
import org.eventhorizon.habitify.database.dao.HabitDao
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import org.eventhorizon.habitify.domain.repository.datastore.OnboardingRepository
import org.eventhorizon.habitify.domain.repository.network.QuoteApiRepository
import org.eventhorizon.habitify.network.QuoteApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides // <-- Изменили @Binds на @Provides
    @Singleton
    fun provideHabitRepository(
        habitDao: HabitDao // <-- Запрашиваем зависимость здесь
    ): HabitRepository {
        // Вручную создаем экземпляр
        return HabitRepositoryImpl(habitDao)
    }

    @Provides
    @Singleton
    fun bindOnboardingRepository(
        dataStore: DataStore<Preferences>
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(
        quoteApiService: QuoteApiService
    ): QuoteApiRepository {
        return QuoteApiRepositoryImpl(quoteApiService)
    }
}