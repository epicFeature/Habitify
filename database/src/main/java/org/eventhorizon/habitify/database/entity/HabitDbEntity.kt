package org.eventhorizon.habitify.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitDbEntity(
    @PrimaryKey(autoGenerate = true) // id будет генерироваться автоматически
    val id: Int = 0,
    val name: String,
    val color: Int,
    val initialDaysToFinish: Int,
    val daysToFinish: Int,
    val statistics: List<HabitStatDb>
)
