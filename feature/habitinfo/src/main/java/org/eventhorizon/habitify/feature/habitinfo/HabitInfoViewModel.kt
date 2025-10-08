package org.eventhorizon.habitify.feature.habitinfo

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.usecase.database.DeleteHabitUseCase
import org.eventhorizon.habitify.domain.usecase.database.GetHabitByIdUseCase
import javax.inject.Inject

@HiltViewModel
class HabitInfoViewModel @Inject constructor(
    private val getHabitByIdUseCase: GetHabitByIdUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val habitId: Int = checkNotNull(savedStateHandle.get<String>("habitId")).toInt()

    private val _state = MutableStateFlow(HabitInfoContract.HabitInfoState.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<HabitInfoContract.HabitInfoEffect>()
    val effect = _effect.receiveAsFlow()

    private var originalHabit: Habit? = null //todo думаю стоит продумать альтернативу

    init {
        loadHabitDetails()
    }

    fun setEvent(event: HabitInfoContract.HabitInfoEvent) {
        when (event) {
            HabitInfoContract.HabitInfoEvent.OnCompleteClick -> completeHabit()
            HabitInfoContract.HabitInfoEvent.OnDeleteClick -> deleteHabit()
        }
    }

    private fun loadHabitDetails() {
        viewModelScope.launch {
            val habit = getHabitByIdUseCase(habitId).first()
            if (habit == null) {
                //todo продумать достаточно ли этой обработки
                _effect.send(HabitInfoContract.HabitInfoEffect.NavigateBackToHome)
                return@launch
            }
            originalHabit = habit
            _state.update {
                it.copy(
                    isLoading = false,
                    habitName = habit.name,
                    daysLeft = habit.daysToFinish,
                    habitColor = Color(habit.color),
                    markedDates = habit.statistics.filter { stat -> stat.isDone }
                        .map { stat -> stat.day }.toSet()
                )
            }
        }
    }

    private fun completeHabit() {
        viewModelScope.launch {
            originalHabit?.let {
                deleteHabitUseCase(it)
                _effect.send(
                    HabitInfoContract.HabitInfoEffect.NavigateBackWithCompletion(
                        showCongrats = true
                    )
                )
            }
        }
    }

    private fun deleteHabit() {
        viewModelScope.launch {
            originalHabit?.let {
                deleteHabitUseCase(it)
                _effect.send(HabitInfoContract.HabitInfoEffect.NavigateBackToHome)
            }
        }
    }
}