package org.eventhorizon.habitify.domain.model.database

data class Habit(
    val id: Int = 0,
    val name: String,
    val color: Long, // Храним цвет как Long (ARGB)
    val daysToFinish: Int,
    val statistics: HabitStatList
)
