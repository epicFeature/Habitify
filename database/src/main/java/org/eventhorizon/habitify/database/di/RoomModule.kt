package org.eventhorizon.habitify.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.eventhorizon.habitify.database.HabitDatabase
import org.eventhorizon.habitify.database.dao.HabitDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideHabitDatabase(@ApplicationContext context: Context): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            HabitDatabase.DATABASE_NAME
        )
            // .addMigrations(HabitDatabase.MIGRATION_1_2) // Здесь добавляются миграции
            .fallbackToDestructiveMigration() // Временно, для разработки. Удаляет и создает базу заново при изменении схемы.
            .build()
    }

    @Provides
    @Singleton
    fun provideHabitDao(database: HabitDatabase): HabitDao {
        return database.habitDao()
    }
}