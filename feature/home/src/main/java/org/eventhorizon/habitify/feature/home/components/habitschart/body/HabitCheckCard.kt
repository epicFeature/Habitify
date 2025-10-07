package org.eventhorizon.habitify.feature.home.components.habitschart.body

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes

@Composable
fun HabitCheckCard(
    modifier: Modifier = Modifier,
    color: Color,
    isChecked: Boolean,
    isClickable: Boolean = false,
    onClick: () -> Unit = {}
) {
    if (isChecked) {
        Box(
            modifier = modifier
                .size(54.dp)
                .background(color.copy(0.1F), Shapes.medium)
                .clickable(enabled = isClickable, onClick = onClick)
                .semantics {
                    contentDescription = if (isClickable) { //todo возможно лучше оптимизировать на будущее
                        "Checkbox for today"
                    } else {
                        "Checkbox for previous day"
                    }
                }
        ) {
            Box(
                modifier = modifier
                    .align(Alignment.Center)
                    .size(50.dp)
                    .background(color, Shapes.medium)
            )
        }
    } else {
        Box(
            modifier = modifier
                .size(54.dp)
                .background(color.copy(0.1F), Shapes.medium)
                .clickable(enabled = isClickable, onClick = onClick)
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
    HabitCheckCard(
        color = AppColor.habitIconColorBlue,
        isChecked = false
    )
}