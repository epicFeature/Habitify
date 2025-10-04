package org.eventhorizon.habitify.database.di

import android.content.Context
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.pluto.plugins.logger.PlutoLog
import com.pluto.plugins.rooms.db.PlutoRoomsDBWatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.eventhorizon.habitify.database.HabitDatabase
import org.eventhorizon.habitify.database.converters.HabitStatListConverter
import org.eventhorizon.habitify.database.dao.HabitDao
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideConverters(gson: Gson): HabitStatListConverter {
        return HabitStatListConverter(gson)
    }

    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        // Создаем глобальный scope для фоновых задач уровня приложения
        return CoroutineScope(SupervisorJob())
    }

    @Provides
    @Singleton
    fun provideHabitDatabase(
        @ApplicationContext context: Context,
        habitStatListConverter: HabitStatListConverter,
        applicationScope: CoroutineScope,
        gson: Gson
    ): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            HabitDatabase.DATABASE_NAME
        )
            // .addMigrations(HabitDatabase.MIGRATION_1_2) // Когда понадобится миграция
            .fallbackToDestructiveMigration() // Временно, для разработки. Удаляет и создает базу заново при изменении схемы.
            .addTypeConverter(habitStatListConverter)
            .setQueryCallback(object : RoomDatabase.QueryCallback {
                override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                    PlutoLog.d("RoomQuery", "SQL Query: $sqlQuery SQL Args: $bindArgs")
                }
            }, Executors.newSingleThreadExecutor()) // Запускаем колбэк в отдельном потоке
            .build()
            .also { database ->
                // ---> НАЧАЛО: ЛОГИРОВАНИЕ ИЗМЕНЕНИЙ <---
                database.invalidationTracker.addObserver(object : InvalidationTracker.Observer("habits") {
                    override fun onInvalidated(tables: Set<String>) {
                        if (tables.contains("habits")) {
                            // 2. Используем scope для запуска корутины
                            applicationScope.launch {
                                // 3. Получаем DAO напрямую из базы данных
                                val habitDao = database.habitDao()
                                // 4. Запрашиваем актуальные данные
                                val allHabits = habitDao.getAllHabits().first() // Предполагая, что у вас есть такой метод
                                // Преобразуем список в JSON-строку
                                val habitsAsJson = gson.toJson(allHabits)
                                // 5. Логируем их
                                PlutoLog.d(
                                    "RoomDataChange",
                                    "Таблица 'habits' изменилась. Новые данные (JSON): $habitsAsJson"
                                )
                            }
                        }
                    }
                })
                // ---> КОНЕЦ <---
                PlutoRoomsDBWatcher.watch(HabitDatabase.DATABASE_NAME, HabitDatabase::class.java)
            }
    }

    @Provides
    @Singleton
    fun provideHabitDao(database: HabitDatabase): HabitDao {
        return database.habitDao()
    }


}