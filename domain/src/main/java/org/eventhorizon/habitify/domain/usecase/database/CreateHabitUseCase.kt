package org.eventhorizon.habitify.domain.usecase.database

import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject


class CreateHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        habitRepository.createHabit(habit)
    }
}