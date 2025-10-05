package org.eventhorizon.habitify.data.mapper

import org.eventhorizon.habitify.database.entity.HabitDbEntity
import org.eventhorizon.habitify.database.entity.HabitStatDb
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat


// Преобразует Entity из базы данных в доменную модель
fun HabitDbEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        color = this.color,
        daysToFinish = this.daysToFinish,
        statistics = this.statistics.map {it.toDomain()}
    )
}

fun HabitStatDb.toDomain(): HabitStat{
    return HabitStat(
        day = this.day,//LocalDate.parse(this.day),
        isDone = this.isDone
    )
}

// Преобразует доменную модель в Entity для сохранения в базу
fun Habit.toEntity(): HabitDbEntity {
    return HabitDbEntity(
        id = this.id,
        name = this.name,
        color = this.color,
        daysToFinish = this.daysToFinish,
        statistics = this.statistics.map { it.toEntity() }
    )
}

fun HabitStat.toEntity(): HabitStatDb {
    return HabitStatDb(
        day = this.day,//.toString(),
        isDone = this.isDone
    )
}