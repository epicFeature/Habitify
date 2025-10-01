package org.eventhorizon.habitify.domain.usecase.database


import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.domain.model.database.HabitStatList
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import org.eventhorizon.habitify.domain.utils.DateUtil
import java.time.LocalDate
import javax.inject.Inject


/**
 * UseCase для отметки о выполнении привычки на определенный день (по умолчанию - сегодня).
 */
class MarkHabitAsDoneUseCase @Inject constructor(
    private val habitRepository: HabitRepository,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val dateUtil: DateUtil // <-- Внедряем зависимость Clock
) {
    /**
     * @param habit Привычка для обновления.
     * @param date Дата, за которую выполняется отметка. Если null, используется текущая дата от clock.
     */
    suspend operator fun invoke(habit: Habit, date: LocalDate? = null) {
        // Если дата не передана, получаем ее из нашей абстракции
        val targetDate = date ?: dateUtil.today()

        val updatedStatistics = habit.statistics.habitStatList.toMutableList()
        val dayStatIndex = updatedStatistics.indexOfFirst { it.day == targetDate }

        if (dayStatIndex != -1) {
            // Если запись за этот день уже есть, обновляем ее
            val currentStat = updatedStatistics[dayStatIndex]
            updatedStatistics[dayStatIndex] = currentStat.copy(isDone = true)
        } else {
            // Если записи нет, добавляем новую
            updatedStatistics.add(HabitStat(day = targetDate, isDone = true))
        }

        // Создаем обновленную привычку и передаем ее в UpdateHabitUseCase
        val updatedHabit = habit.copy(statistics = HabitStatList(updatedStatistics))
        updateHabitUseCase(updatedHabit)
    }
}