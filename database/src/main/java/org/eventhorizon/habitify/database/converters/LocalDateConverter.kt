package org.eventhorizon.habitify.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate


/**
 * Простой конвертер для LocalDate, не имеющий зависимостей.
 * Room и Pluto смогут создать его без Hilt.
 */

class LocalDateConverter {
    /**
     * Конвертер для преобразования Long в LocalDate.
     * Room будет вызывать этот метод при чтении данных из базы.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        // Преобразуем количество дней с начала эпохи обратно в дату
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    /**
     * Конвертер для преобразования LocalDate в Long.
     * Room будет вызывать этот метод при записи данных в базу.
     */
    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        // Преобразуем дату в количество дней с начала эпохи
        return date?.toEpochDay()
    }
/*    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let {
            LocalDate.parse(it, formatter)
        }
    }*/
}