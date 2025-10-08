package org.eventhorizon.habitify.database.entity

import java.time.LocalDate


data class HabitStatDb(
    val day: LocalDate,
    val isDone: Boolean
)
