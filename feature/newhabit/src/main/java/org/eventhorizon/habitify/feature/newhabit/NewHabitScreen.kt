package org.eventhorizon.habitify.feature.newhabit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.eventhorizon.habitify.feature.newhabit.components.appearance.HabitAppearanceCard
import org.eventhorizon.habitify.feature.newhabit.components.btn.DeleteBtn
import org.eventhorizon.habitify.feature.newhabit.components.btn.DoneBtn
import org.eventhorizon.habitify.feature.newhabit.components.duration.HabitDurationPlan
import org.eventhorizon.habitify.feature.newhabit.components.topcard.HabitNameCard
import org.eventhorizon.habitify.ui.components.theme.AppColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewHabitScreen(
    modifier: Modifier,
    onNavigateHome: () -> Unit,
    viewModel: NewHabitViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(NewHabitContract.NewHabitUiEvent.OnSetState)
    }

    // 2. Слушаем одноразовые эффекты (например, для навигации)
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                // Если пришел эффект "NavigateToHome", вызываем колбэк
                NewHabitContract.NewHabitEffect.NavigateToHome -> onNavigateHome()
            }
        }
    }

    Column(modifier = modifier.padding(20.dp)) {
        HabitNameCard(
            modifier = Modifier,
            habitName = state.habit.name,
            habitColor = state.habit.color,
            onHabitNameChanged = { viewModel.handleEvent(NewHabitContract.NewHabitUiEvent.OnHabitNameChanged(it)) }
        )
        Spacer(Modifier.size(8.dp))
        HabitAppearanceCard(
            modifier = Modifier,
            habitName = state.habit.name,
            color = state.habit.color,
            appearanceDays = state.appearanceDays
        )
        Spacer(Modifier.size(8.dp))
        HabitDurationPlan(
            modifier = Modifier,
            color = state.habit.color,
            onDaysSelected = { days -> viewModel.handleEvent(NewHabitContract.NewHabitUiEvent.OnDurationChanged(days)) }
        )
        Spacer(Modifier.weight(1F))
        Row (modifier=Modifier.fillMaxWidth()){
            DeleteBtn(onClick = { viewModel.handleEvent(NewHabitContract.NewHabitUiEvent.OnDeleteClick) })
            Spacer(Modifier.weight(1F))
            DoneBtn (onClick = { viewModel.handleEvent(NewHabitContract.NewHabitUiEvent.OnDoneClick) }, enabled = state.isDoneButtonEnabled)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewNewHabitScreen() {
    NewHabitScreen(
        modifier = Modifier
            .background(AppColor.BgColorLightOrange),
        onNavigateHome = {}
    )
}