package org.eventhorizon.habitify.feature.newhabit

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

@Composable
fun NewHabitScreen(
    modifier: Modifier,
    onNavigateHome: () -> Unit,
    viewModel: NewHabitViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { //для обновления цвета
        viewModel.handleEvent(NewHabitContract.NewHabitUiEvent.ResetAndPrepareState)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                NewHabitContract.NewHabitEffect.NavigateToHome -> onNavigateHome()
            }
        }
    }

    // "Глупая" версия -> отвечает только за отрисовку
    NewHabitScreenContent(
        modifier = modifier,
        state = state,
        onEvent = viewModel::handleEvent
    )
}

//такая реализация удобна для тестирования
@Composable
fun NewHabitScreenContent(
    modifier: Modifier = Modifier,
    state: NewHabitContract.NewHabitUiState.State,
    onEvent: (NewHabitContract.NewHabitUiEvent) -> Unit
){
    Column(modifier = modifier.padding(20.dp)) {
        HabitNameCard(
            modifier = Modifier,
            habitName = state.habit.name,
            habitColor = state.habit.color,
            onHabitNameChanged = {
                onEvent(
                    NewHabitContract.NewHabitUiEvent.OnHabitNameChanged(
                        it
                    )
                )
            }
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
            onDaysSelected = { days ->
                onEvent(
                    NewHabitContract.NewHabitUiEvent.OnDurationChanged(
                        days
                    )
                )
            }
        )
        Spacer(Modifier.weight(1F))
        Row(modifier = Modifier.fillMaxWidth()) {
            DeleteBtn(onClick = { onEvent(NewHabitContract.NewHabitUiEvent.OnDeleteClick) })
            Spacer(Modifier.weight(1F))
            DoneBtn(
                onClick = { onEvent(NewHabitContract.NewHabitUiEvent.OnDoneClick) },
                enabled = state.isDoneButtonEnabled
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewNewHabitScreen() {
    NewHabitScreen(
        modifier = Modifier
            .background(AppColor.BgColorLightOrange),
        onNavigateHome = {}
    )
}