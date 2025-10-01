package org.eventhorizon.habitify.domain.usecase.database

import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject

/**
 * UseCase для создания новой привычки с предварительной валидацией.
 */
class CreateHabitUseCase @Inject constructor( //todo определиться хорошо ли так отправлять ошибки не обработанные?
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        // Бизнес-правило: нельзя создать привычку с пустым названием.
        if (habit.name.isBlank()) {
            throw IllegalArgumentException("Habit name cannot be blank.")
        }
        // Бизнес-правило: количество дней должно быть положительным.
        if (habit.daysToFinish <= 0) {
            throw IllegalArgumentException("Days to finish must be a positive number.")
        }
        habitRepository.createHabit(habit)
    }
}