package org.eventhorizon.habitify.database.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.pluto.plugins.rooms.db.PlutoRoomsDBWatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.eventhorizon.habitify.database.HabitDatabase
import org.eventhorizon.habitify.database.converters.Converters
import org.eventhorizon.habitify.database.dao.HabitDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideConverters(gson: Gson): Converters {
        return Converters(gson)
    }

    @Provides
    @Singleton
    fun provideHabitDatabase(@ApplicationContext context: Context, converters: Converters): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            HabitDatabase.DATABASE_NAME
        )
            .addTypeConverter(converters)
            // .addMigrations(HabitDatabase.MIGRATION_1_2) // Когда понадобится миграция
            .fallbackToDestructiveMigration() // Временно, для разработки. Удаляет и создает базу заново при изменении схемы.
            .build()
            .also {
                PlutoRoomsDBWatcher.watch(HabitDatabase.DATABASE_NAME, HabitDatabase::class.java)
            }
    }

    @Provides
    @Singleton
    fun provideHabitDao(database: HabitDatabase): HabitDao {
        return database.habitDao()
    }


}