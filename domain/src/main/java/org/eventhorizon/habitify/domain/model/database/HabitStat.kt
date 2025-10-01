package org.eventhorizon.habitify.domain.model.database

import java.time.LocalDate

// Класс для хранения данных о статистике по дням
data class HabitStat(
    val day: LocalDate, // LocalDate отлично подходит для хранения даты
    val isDone: Boolean
)
