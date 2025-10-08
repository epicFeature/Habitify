package org.eventhorizon.habitify.domain.model.database

data class Habit(
    val id: Int = 0,
    val name: String,
    val color: Int,
    val initialDaysToFinish: Int,
    val daysToFinish: Int,
    val statistics: List<HabitStat>
)
