package org.eventhorizon.habitify.data.repositories.database

import android.os.Build
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAllHabits(): Flow<List<Habit>> {
        return habitDao.getAllHabits().map { entityList ->
            entityList.map { it.toDomain() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getHabitById(id: Int): Flow<Habit?> {
        return habitDao.getHabitById(id).map { habitEntity ->
            habitEntity?.toDomain()
        }
    }

    override suspend fun createHabit(habit: Habit) {
        habitDao.insertHabit(habit.toEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toEntity())
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit.toEntity())
    }
}