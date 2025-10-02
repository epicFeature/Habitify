package org.eventhorizon.habitify.feature.newhabit.model

import androidx.compose.ui.graphics.Color
import org.eventhorizon.habitify.ui.components.theme.AppColor


/**
 * Модель привычки для слоя представления (UI).
 * Содержит данные в формате, удобном для отображения.
 */
data class HabitUi(
    val name: String = "",
    val color: Color = AppColor.habitIconColorYellow, // Используем Color, удобный для Compose
    val targetDays: Int = 1 // Значение по умолчанию
)