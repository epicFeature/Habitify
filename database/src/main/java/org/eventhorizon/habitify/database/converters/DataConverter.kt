package org.eventhorizon.habitify.database.converters


/**
 * Простой конвертер для LocalDate, не имеющий зависимостей.
 * Room и Pluto смогут создать его без Hilt.
 */
//class DateConverter {
//    @RequiresApi(Build.VERSION_CODES.O)
//    @TypeConverter
//    fun fromTimestamp(value: String?): LocalDate? {
//        return value?.let { LocalDate.parse(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: LocalDate?): String? {
//        return date?.toString()
//    }
//}