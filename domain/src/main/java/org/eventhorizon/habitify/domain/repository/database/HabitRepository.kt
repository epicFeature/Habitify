package org.eventhorizon.habitify.domain.repository.database

import kotlinx.coroutines.flow.Flow
import org.eventhorizon.habitify.domain.model.database.Habit

interface HabitRepository {
    fun getAllHabits(): Flow<List<Habit>>
    fun getHabitById(id: Int): Flow<Habit?>
    suspend fun createHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
}