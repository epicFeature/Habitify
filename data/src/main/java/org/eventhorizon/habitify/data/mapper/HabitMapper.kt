package org.eventhorizon.habitify.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import org.eventhorizon.habitify.database.entity.HabitDbEntity
import org.eventhorizon.habitify.database.entity.HabitStatDb
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import java.time.LocalDate


// Преобразует Entity из базы данных в доменную модель
@RequiresApi(Build.VERSION_CODES.O)
fun HabitDbEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        color = this.color,
        daysToFinish = this.daysToFinish,
        statistics = this.statistics.map {
            HabitStat(
                LocalDate.parse(it.day),
                it.isDone
            )
        }
    )
}

// Преобразует доменную модель в Entity для сохранения в базу
fun Habit.toEntity(): HabitDbEntity {
    return HabitDbEntity(
        id = this.id,
        name = this.name,
        color = this.color,
        daysToFinish = this.daysToFinish,
        statistics = this.statistics.map { HabitStatDb(it.day.toString(), it.isDone) }
    )
}