package org.eventhorizon.habitify.feature.newhabit.mapper

import androidx.compose.ui.graphics.toArgb
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.feature.newhabit.model.HabitUi
import java.time.LocalDate

fun HabitUi.toDomain(): Habit {
    val today = LocalDate.now()
    val dateRange = (0..4L).map { dayIndex -> //сегодня - 4 дня
        today.minusDays(dayIndex)
    }
    val initialStatistics = dateRange.map { date ->
        HabitStat(day = date, isDone = false)
    }
    return Habit(
        name = this.name,
        color = this.color.toArgb(), //Compose Color в Int (ARGB) для бд
        initialDaysToFinish = this.targetDays,
        daysToFinish = this.targetDays,
        statistics = initialStatistics
    )
}