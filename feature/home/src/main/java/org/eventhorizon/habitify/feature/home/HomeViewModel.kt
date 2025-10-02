package org.eventhorizon.habitify.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.eventhorizon.habitify.domain.usecase.database.GetAllHabitsUseCase
import org.eventhorizon.habitify.domain.usecase.database.UpdateHabitUseCase
import org.eventhorizon.habitify.domain.usecase.network.GetQuotesUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllHabitsUseCase: GetAllHabitsUseCase,
    updateHabitUseCase: UpdateHabitUseCase,
    getQuotesUseCase: GetQuotesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow< HomeContract.HomeUiState.State>(
        HomeContract.HomeUiState.State()
    )
    val uiState = _uiState.asStateFlow()

    init{

    }

    fun handleEvent(event: HomeContract.HomeUiEvent) {
        when (event) {
            is HomeContract.HomeUiEvent.OnHabitCardClick -> onHabitCardClick(event.habitId)
        }
    }

    private fun onHabitCardClick(habitId: Long) {
        TODO("Not yet implemented")
    }
}