package org.eventhorizon.habitify.ui.screens.home.components.habitschart.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes

@Composable
fun HabitCheckCard(modifier: Modifier = Modifier, color: Color, isChecked: Boolean = false) { //todo передавать динамически цвет
    if (isChecked){
        Box(modifier = modifier
            .size(54.dp)
            .background(color.copy(0.1F), Shapes.medium)
        ){
        Box(modifier = modifier
            .align(Alignment.Center)
            .size(50.dp)
            .background(color, Shapes.medium)
        )}
    } else {
        Box(modifier = modifier
            .size(54.dp)
            .background(color.copy(0.1F), Shapes.medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitCheckedCard() {
    HabitCheckCard(color = AppColor.habitIconColorBlue, isChecked = true)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitUncheckedCard() {
    HabitCheckCard(color = AppColor.habitIconColorBlue, isChecked = false)
}