package org.eventhorizon.habitify.feature.newhabit.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.feature.newhabit.model.HabitUi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun HabitUi.toDomain(): Habit {
    return Habit(
        name = this.name,
        // Преобразуем Compose Color в строку (например, HEX) для хранения в БД
        color = "#${this.color.value.toULong().toString(16)}",
        daysToFinish = this.targetDays, // daysToFinish изначально равен targetDays
        statistics = listOf(HabitStat(day=LocalDate.now(), isDone = false)) // Начальная статистика пуста
    )
}