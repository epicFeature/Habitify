package org.eventhorizon.habitify.ui.screens.newhabit.components.duration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes
import org.eventhorizon.habitify.ui.theme.homeChartHabitTitleTextStyle
import kotlin.math.roundToInt

@Composable
fun HabitDurationPlan(modifier: Modifier,
                      onDaysSelected: (Int) -> Unit = {},
                      color: Color = AppColor.habitIconColorYellow
) {
    val days = listOf(1, 3, 5, 7, 10)
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    // Рассчитываем текущее количество дней на основе позиции слайдера
    val currentDays = days[sliderPosition.roundToInt()]

    Column(modifier = modifier.fillMaxWidth().background(AppColor.White, Shapes.medium).padding(18.dp)) {
        Text(
            text = "Дней до завершения: $currentDays",
            modifier = Modifier.padding(bottom = 16.dp),
            style =  homeChartHabitTitleTextStyle
        )

        Slider(
            value = sliderPosition,
            onValueChange = { newValue ->
                sliderPosition = newValue
                onDaysSelected(days[newValue.roundToInt()])
            },
            valueRange = 0f..(days.size - 1).toFloat(),
            steps = days.size - 2, // Создаем фиксированные позиции
            modifier = Modifier.fillMaxWidth(),
            colors = SliderColors(
                thumbColor = color,
                activeTrackColor = color,
                activeTickColor = color,
                inactiveTrackColor = color.copy(0.2F),
                inactiveTickColor = color,
                disabledThumbColor = color.copy(0.5F),
                disabledActiveTrackColor = color.copy(0.5F),
                disabledActiveTickColor = color.copy(0.5F),
                disabledInactiveTrackColor = color.copy(0.3F),
                disabledInactiveTickColor = color.copy(0.5F)
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewHabitDurationPlan() {
    HabitDurationPlan(
        modifier = Modifier
    )
}