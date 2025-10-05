package org.eventhorizon.habitify.database.entity

import java.time.LocalDate


data class HabitStatDb(//статистика по дням
    val day: LocalDate,
    val isDone: Boolean
)
