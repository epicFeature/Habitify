package org.eventhorizon.habitify.data.mapper

import org.eventhorizon.habitify.database.entity.HabitDbEntity
import org.eventhorizon.habitify.database.entity.HabitStatDb
import org.eventhorizon.habitify.database.entity.HabitStatListDb
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.domain.model.database.HabitStatList


// Преобразует Entity из базы данных в доменную модель
fun HabitDbEntity.toDomain(): Habit {
    return Habit(
        id = this.id,
        name = this.name,
        color = this.color,
        daysToFinish = this.daysToFinish,
        statistics = HabitStatList(this.statistics.habitStatDbList.map { HabitStat(it.day, it.isDone) })
    )
}

// Преобразует доменную модель в Entity для сохранения в базу
fun Habit.toEntity(): HabitDbEntity {
    return HabitDbEntity(
        id = this.id,
        name = this.name,
        color = this.color,
        daysToFinish = this.daysToFinish,
        statistics = HabitStatListDb(this.statistics.habitStatList.map { HabitStatDb(it.day, it.isDone) })
    )
}