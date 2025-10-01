package org.eventhorizon.habitify.domain.usecase.database

import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject


/**
 * UseCase для обновления существующей привычки.
 */
class UpdateHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        // Валидация аналогична созданию
        if (habit.name.isBlank()) {
            throw IllegalArgumentException("Habit name cannot be blank.")
        }
        habitRepository.updateHabit(habit)
    }
}