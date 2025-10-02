package org.eventhorizon.habitify.domain.model.database

data class Habit(
    val id: Int = 0,
    val name: String,
    val color: String,
    val daysToFinish: Int,
    val statistics: List<HabitStat>
)
