package org.eventhorizon.habitify.feature.newhabit.model

import androidx.compose.ui.graphics.Color
import org.eventhorizon.habitify.ui.components.theme.AppColor


data class HabitUi(
    val name: String = "",
    val color: Color = AppColor.habitIconColorYellow,
    val targetDays: Int = 1
)