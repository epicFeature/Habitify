package org.eventhorizon.habitify.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.eventhorizon.habitify.database.entity.HabitStatDb


@ProvidedTypeConverter
class HabitStatListConverter(private val gson: Gson) {
    @TypeConverter
    fun fromHabitStatList(statList: List<HabitStatDb>?): String? {
        return statList?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toHabitStatList(json: String?): List<HabitStatDb>? {
        if (json == null) return null
        val type = object : TypeToken<List<HabitStatDb>>() {}.type
        return gson.fromJson(json, type)
    }
}