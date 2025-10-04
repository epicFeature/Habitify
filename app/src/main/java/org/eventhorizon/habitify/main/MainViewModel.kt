package org.eventhorizon.habitify.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.eventhorizon.habitify.domain.usecase.datastore.FinishOnboardingUseCase
import org.eventhorizon.habitify.domain.usecase.datastore.GetOnboardingStatusUseCase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val finishOnboardingUseCase: FinishOnboardingUseCase,
    private val getOnboardingStatusUseCase: GetOnboardingStatusUseCase
): ViewModel() {

    val startDestination: StateFlow<StartDestination> = getOnboardingStatusUseCase.invoke()
        .map { isOnboardingFinished ->
            if (isOnboardingFinished) {
                StartDestination.Main
            } else {
                StartDestination.Onboarding
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = StartDestination.Loading
        )

    fun onOnboardingFinished() {
        viewModelScope.launch {
            finishOnboardingUseCase()
        }
    }
}



sealed class StartDestination {
    object Loading : StartDestination()
    object Onboarding : StartDestination()
    object Main : StartDestination()
}
