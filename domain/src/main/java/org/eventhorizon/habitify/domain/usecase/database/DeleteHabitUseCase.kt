package org.eventhorizon.habitify.domain.usecase.database

import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject

/**
 * UseCase для удаления привычки.
 */
class DeleteHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        habitRepository.deleteHabit(habit)
    }
}