package org.eventhorizon.habitify.ui.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes


@Composable
fun HabitCheckCalendarCard(modifier: Modifier = Modifier, color: Color, isChecked: Boolean = false) {
    if (isChecked){
        Box(modifier = modifier
            .size(38.dp)
            .background(color.copy(0.1F), Shapes.medium)
        ){
            Box(modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .background(color, Shapes.medium)
                .align(Alignment.Center)
            )}
    } else {
        Box(modifier =  modifier
            .size(38.dp)
            .background(color.copy(0.1F), Shapes.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitCheckedCalendarCard() {
    HabitCheckCalendarCard(color = AppColor.habitIconColorYellow, isChecked = true)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitUncheckedCalendarCard() {
    HabitCheckCalendarCard(color = AppColor.habitIconColorYellow, isChecked = false)
}