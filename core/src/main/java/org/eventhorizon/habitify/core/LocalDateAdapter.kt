package org.eventhorizon.habitify.core

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Используем стандартный формат ISO_LOCAL_DATE ("2011-12-03")
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    override fun serialize(
        src: LocalDate?,
        typeOfSrc: java.lang.reflect.Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        // Преобразуем дату в строку и оборачиваем в JsonPrimitive
        return src?.let { JsonPrimitive(it.format(formatter)) }
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: java.lang.reflect.Type?,
        context: JsonDeserializationContext?
    ): LocalDate? {
        // Преобразуем строку из JSON обратно в дату
        return json?.asString?.let { LocalDate.parse(it, formatter) }
    }
}