package org.eventhorizon.habitify.data.repositories.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.eventhorizon.habitify.data.mapper.toDomain
import org.eventhorizon.habitify.data.mapper.toEntity
import org.eventhorizon.habitify.database.dao.HabitDao
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitRepository {

    override fun getAllHabits(): Flow<List<Habit>> {
        // Получаем Flow<List<HabitEntity>> и преобразуем его в Flow<List<Habit>>
        return habitDao.getAllHabits().map { entityList ->
            entityList.map { it.toDomain() }
        }
    }

    override fun getHabitById(id: Int): Flow<Habit?> {
        return habitDao.getHabitById(id).map { habitEntity ->
            // Преобразуем Entity в доменную модель.
            // Если habitEntity равен null, map вернет null.
            habitEntity?.toDomain()
        }
    }

    override suspend fun createHabit(habit: Habit) {
        // Преобразуем доменную модель в entity перед вставкой
        habitDao.insertHabit(habit.toEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit.toEntity())
    }
}