package org.eventhorizon.habitify.database.entity

import java.time.LocalDate

// Класс для хранения данных о статистике по дням
data class HabitStatDb(
    val day: LocalDate, // LocalDate отлично подходит для хранения даты
    val isDone: Boolean
)
