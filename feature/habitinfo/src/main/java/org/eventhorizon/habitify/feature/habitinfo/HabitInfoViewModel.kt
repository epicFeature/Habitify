package org.eventhorizon.habitify.feature.habitinfo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.eventhorizon.habitify.domain.usecase.database.DeleteHabitUseCase
import javax.inject.Inject

@HiltViewModel
class HabitInfoViewModel @Inject constructor(
    deleteHabitUseCase: DeleteHabitUseCase
): ViewModel() {
    //private val _uiState = MutableStateFlow<>
}