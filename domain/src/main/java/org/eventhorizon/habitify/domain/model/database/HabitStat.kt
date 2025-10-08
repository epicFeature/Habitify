package org.eventhorizon.habitify.domain.model.database

import java.time.LocalDate

data class HabitStat(
    val day: LocalDate,
    val isDone: Boolean
)
