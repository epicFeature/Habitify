package org.eventhorizon.habitify.database.entity

// Класс для хранения данных о статистике по дням
data class HabitStatDb(
    val day: String, // LocalDate отлично подходит для хранения даты
    val isDone: Boolean
)
