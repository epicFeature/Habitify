package org.eventhorizon.habitify.core

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Основной модуль для предоставления "сквозных" зависимостей,
 * используемых в разных частях приложения (например, в RoomModule и NetworkModule).
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    /**
     * Предоставляет единственный экземпляр Gson для всего приложения.
     * Аннотация @Singleton гарантирует, что будет создан только один объект,
     * который будет переиспользоваться везде, где он требуется.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}