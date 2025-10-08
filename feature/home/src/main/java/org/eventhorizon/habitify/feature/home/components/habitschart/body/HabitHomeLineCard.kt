package org.eventhorizon.habitify.feature.home.components.habitschart.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.HomeContract
import org.eventhorizon.habitify.ui.components.theme.AppColor
import java.time.LocalDate

@Composable
fun HabitHomeLineCard(
    modifier: Modifier = Modifier,
    color: Color,
    checks: List<HomeContract.HomeUiState.State.HabitCheck>,
    onCheckClick: (LocalDate, Boolean) -> Unit
) {
    Row(
        modifier
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        checks.forEach { check ->
            HabitCheckCard(
                color = color,
                isChecked = check.isChecked,
                isClickable = check.isClickable,
                onClick = { onCheckClick(check.date, check.isChecked) }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewHabitHomeLineCard() {
    val today = LocalDate.now()
    val previewChecks = listOf(
        HomeContract.HomeUiState.State.HabitCheck(
            today.minusDays(4),
            isChecked = true,
            isClickable = false
        ),
        HomeContract.HomeUiState.State.HabitCheck(
            today.minusDays(3),
            isChecked = false,
            isClickable = false
        ),
        HomeContract.HomeUiState.State.HabitCheck(
            today.minusDays(2),
            isChecked = true,
            isClickable = false
        ),
        HomeContract.HomeUiState.State.HabitCheck(
            today.minusDays(1),
            isChecked = false,
            isClickable = false
        ),
        HomeContract.HomeUiState.State.HabitCheck(
            today,
            isChecked = true,
            isClickable = true
        ) // сегодня кликабельный, ост нет
    )

    HabitHomeLineCard(
        checks = previewChecks,
        onCheckClick = { _, _ -> },
        modifier = Modifier,
        color = AppColor.habitIconColorBlue
    )
}