package org.eventhorizon.habitify.feature.newhabit.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.toArgb
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.feature.newhabit.model.HabitUi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun HabitUi.toDomain(): Habit {
    // 1. Получаем сегодняшнюю дату
    val today = LocalDate.now()

    // 2. Создаем список дат: сегодня и 4 предыдущих дня
    val dateRange = (0..4L).map { dayIndex ->
        today.minusDays(dayIndex)
    }

    // 3. Создаем список статистики для этих дат
    val initialStatistics = dateRange.map { date ->
        HabitStat(day = date, isDone = false)
    }

    return Habit(
        name = this.name,
        // Преобразуем Compose Color в Int (ARGB) для хранения в БД
        color = this.color.toArgb(),
        initialDaysToFinish = this.targetDays,
        daysToFinish = this.targetDays,
        // 4. Подставляем созданный список статистики
        statistics = initialStatistics
    )
}