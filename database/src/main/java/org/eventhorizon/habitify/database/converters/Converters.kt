package org.eventhorizon.habitify.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.eventhorizon.habitify.database.entity.HabitStatListDb
import javax.inject.Inject

class Converters @Inject constructor(val gson: Gson) {
    @TypeConverter
    fun fromHabitStatList(habitStatListDb: HabitStatListDb?): String? {
        return habitStatListDb?.let { gson.toJson(it) }
    }

    // Конвертер для извлечения списка статистики из базы данных
    @TypeConverter
    fun toHabitStatList(json: String?): HabitStatListDb? {
        return json?.let { gson.fromJson(it, HabitStatListDb::class.java) }
    }
}