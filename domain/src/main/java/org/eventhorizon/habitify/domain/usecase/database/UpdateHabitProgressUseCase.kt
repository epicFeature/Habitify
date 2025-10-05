package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.flow.first
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import java.time.LocalDate
import javax.inject.Inject

class UpdateHabitProgressUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    /**
     * Обновляет прогресс привычки. Если привычка завершена, удаляет ее.
     * @return `true` если привычка была завершена и удалена, иначе `false`.
     */
    suspend operator fun invoke(habitId: Int, date: LocalDate, isChecked: Boolean): Boolean {
        // 1. Получаем актуальный объект Habit из репозитория
        val habit = habitRepository.getHabitById(habitId).first() ?: return false

        // 2. Создаем изменяемую копию списка статистики
        val stats = habit.statistics.toMutableList()
        val statIndex = stats.indexOfFirst { it.day.isEqual(date) }
        val newIsDoneValue = !isChecked

        // 3. Обновляем или добавляем запись в списке
        // Эта логика корректна, так как она работает с простым списком
        if (statIndex != -1) {
            // Если запись для этой даты существует, обновляем ее
            stats[statIndex] = stats[statIndex].copy(isDone = newIsDoneValue)
        } else {
            // Если записи нет, добавляем новую.
            // ID привычки не нужен, так как HabitStat не имеет такого поля.
            stats.add(HabitStat(day = date, isDone = newIsDoneValue))
        }

        // 4. Пересчитываем количество выполненных дней
        val doneCount = stats.count { it.isDone }
        val newDaysToFinish = habit.initialDaysToFinish - doneCount

        // 5. Проверяем, завершена ли привычка
        if (newDaysToFinish <= 0) {
            // Привычка завершена, удаляем ее
            habitRepository.deleteHabit(habit)
            return true
        } else {
            // Привычка не завершена, создаем обновленный объект Habit
            val updatedHabit = habit.copy(
                statistics = stats, // Подставляем обновленный список статистики
                daysToFinish = newDaysToFinish // Подставляем новое количество оставшихся дней
            )
            // И сохраняем весь объект целиком
            habitRepository.updateHabit(updatedHabit)
            return false
        }
    }
}