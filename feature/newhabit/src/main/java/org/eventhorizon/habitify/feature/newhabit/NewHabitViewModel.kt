package org.eventhorizon.habitify.feature.newhabit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.eventhorizon.habitify.domain.usecase.database.CreateHabitUseCase
import org.eventhorizon.habitify.feature.newhabit.mapper.toDomain
import org.eventhorizon.habitify.ui.components.theme.AppColor
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class NewHabitViewModel @Inject constructor(
    private val createHabitUseCase: CreateHabitUseCase
) : ViewModel(){
    private val _state = MutableStateFlow< NewHabitContract.NewHabitUiState.State>(
        NewHabitContract.NewHabitUiState.State()
    )
    val state = _state.asStateFlow()

    private val _effect = Channel<NewHabitContract.NewHabitEffect>()
    val effect = _effect.receiveAsFlow()

    private val colorCycle = listOf(
        AppColor.habitIconColorYellow,
        AppColor.habitIconColorPurple,
        AppColor.habitIconColorBlue,
        AppColor.habitIconColorRed,
    )
    private var colorIndex = 0

    init{
        // Начальная инициализация состояния
        resetState()
    }

    fun resetState(){
        val appearanceDays = MutableList(7) { it < 5 }.apply {shuffle(Random) }
        _state.update {
            it.copy(
                // Обновляем вложенный объект habit
                habit = it.habit.copy(color = getNextColor()),
                appearanceDays = appearanceDays,
                isDoneButtonEnabled = false
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun handleEvent(event: NewHabitContract.NewHabitUiEvent) {
        when (event) {
            is NewHabitContract.NewHabitUiEvent.OnHabitNameChanged -> onHabitNameChanged(event.name)
            is NewHabitContract.NewHabitUiEvent.OnDurationChanged -> onDurationChanged(event.days)
            is NewHabitContract.NewHabitUiEvent.OnDoneClick -> onDoneClick()
            is NewHabitContract.NewHabitUiEvent.OnDeleteClick -> onDeleteClick()
        }

    }

    private fun onHabitNameChanged(name: String) {
        _state.update {
            it.copy(
                habit = it.habit.copy(name = name),
                isDoneButtonEnabled = name.isNotBlank() // Кнопка активна, если имя не пустое
            )
        }
    }

    private fun onDurationChanged(days: Int) {
        _state.update {
            it.copy(
                habit = it.habit.copy(targetDays = days)
            )
        }
    }

    private fun onDeleteClick() {
        viewModelScope.launch {
            _effect.send(NewHabitContract.NewHabitEffect.NavigateToHome)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onDoneClick() {
        viewModelScope.launch {
            // <-- 6. ИСПОЛЬЗУЕМ МАППЕР ДЛЯ ПРЕОБРАЗОВАНИЯ
            val habitToSave = _state.value.habit.toDomain()

            // Оборачиваем в try-catch на случай ошибок валидации из UseCase
            try {
                createHabitUseCase(habitToSave)
                _effect.send(NewHabitContract.NewHabitEffect.NavigateToHome)
            } catch (e: IllegalArgumentException) {
                // В будущем здесь можно будет показать ошибку пользователю
                // _effect.send(NewHabitEffect.ShowError(e.message ?: "An error occurred"))
                println("Error creating habit: ${e.message}")
            }
        }
    }

    private fun getNextColor(): Color {
        val color = colorCycle[colorIndex]
        colorIndex = (colorIndex + 1) % colorCycle.size
        return color
    }
}