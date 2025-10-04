package org.eventhorizon.habitify.database.entity


data class HabitStatDb(//статистика по дням
    val day: String,
    val isDone: Boolean
)
