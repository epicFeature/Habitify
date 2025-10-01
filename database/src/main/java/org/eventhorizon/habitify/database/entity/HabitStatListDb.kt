package org.eventhorizon.habitify.database.entity

// Обертка для списка, чтобы TypeConverter корректно работал
data class HabitStatListDb(
    val habitStatDbList: List<HabitStatDb>
)