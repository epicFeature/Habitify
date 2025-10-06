package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.flow.first
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import java.time.LocalDate
import javax.inject.Inject

class UpdateHabitProgressUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habitId: Int, date: LocalDate, isChecked: Boolean): Boolean {
        // актуальная Habit из repository
        val habit = habitRepository.getHabitById(habitId).first() ?: return false

        // копия листа статистики
        val stats = habit.statistics.toMutableList()
        val statIndex = stats.indexOfFirst { it.day.isEqual(date) }
        val newIsDoneValue = !isChecked

        // обновляем или добавляем инфу в лист
        if (statIndex != -1) { //запись для этой даты уже есть в бд -> обновляем её
            stats[statIndex] = stats[statIndex].copy(isDone = newIsDoneValue)
        } else {//записи для этой даты нет -> создаем её
            stats.add(HabitStat(day = date, isDone = newIsDoneValue))
        }

        // подсчёт кол-ва дней с isDone
        val doneCount = stats.count { it.isDone }
        val newDaysToFinish = habit.initialDaysToFinish - doneCount

        // проверка не завершена ли привычка
        if (newDaysToFinish <= 0) { // привычка завершена
            habitRepository.deleteHabit(habit) // удаляем её из бд
            return true
        } else { // привычка не завершена -> обновляем Habit в бд
            val updatedHabit = habit.copy(
                statistics = stats, // обновленная статистика
                daysToFinish = newDaysToFinish // новое кол-во ост дней
            )
            // сохраняем обновленный вариант в бд в бд
            habitRepository.updateHabit(updatedHabit)
            return false
        }
    }
}