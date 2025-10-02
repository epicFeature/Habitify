package org.eventhorizon.habitify.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.eventhorizon.habitify.database.entity.HabitStatDb


@ProvidedTypeConverter // Важная аннотация, так как мы зависим от Gson
class Converters(private val gson: Gson) {

    /**
     * Конвертирует список объектов HabitStatDb в строку JSON.
     * Room будет вызывать этот метод при сохранении HabitDbEntity.
     */
    @TypeConverter
    fun fromHabitStatList(statistics: List<HabitStatDb>?): String? {
        // Если список null, возвращаем null. Иначе, преобразуем в JSON.
        return statistics?.let { gson.toJson(it) }
    }

    /**
     * Конвертирует строку JSON обратно в список объектов HabitStatDb.
     * Room будет вызывать этот метод при чтении HabitDbEntity из базы данных.
     */
    @TypeConverter
    fun toHabitStatList(json: String?): List<HabitStatDb>? {
        if (json == null) {
            return null
        }
        // TypeToken необходим, чтобы Gson понял, что мы хотим получить именно List<HabitStatDb>,
        // а не просто список или массив. Это нужно из-за стирания типов в Java/Kotlin.
        val type = object : TypeToken<List<HabitStatDb>>() {}.type
        return gson.fromJson(json, type)
    }
}