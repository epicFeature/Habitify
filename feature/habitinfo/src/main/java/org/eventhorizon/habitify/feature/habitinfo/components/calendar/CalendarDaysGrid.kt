package org.eventhorizon.habitify.feature.habitinfo.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.eventhorizon.habitify.ui.components.theme.AppColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDaysGrid(modifier: Modifier = Modifier, viewModel: CalendarViewModel) {
    //todo надо будет перепроверить и скорректировать
    val days = viewModel.getDays()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7)
    ) {
        items(days.size) { index ->
            val date = days[index]
            CalendarDay(
                date = date,
                isCurrentMonth = viewModel.isCurrentMonth(date),
                isChecked = viewModel.habitData[date] ?: false,
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 4.dp),
                color = AppColor.habitIconColorYellow
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDaysGrid() {
    CalendarDaysGrid(
        modifier = Modifier.background(AppColor.White),
        viewModel = viewModel()
    )
}