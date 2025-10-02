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


    /**
     * StateFlow, который определяет, какой экран должен быть показан при запуске.
     * UI (ваш NavHost в MainAppScreen) подписывается на этот Flow.
     *
     * - `getOnboardingStatusUseCase()`: Получаем Flow<Boolean> из domain слоя. ViewModel не знает, откуда он берется (DataStore, SharedPreferences и т.д.).
     * - `.map`: Преобразуем значение boolean в один из наших классов состояний StartDestination.
     * - `.stateIn`: Превращаем "холодный" Flow в "горячий" StateFlow, который хранит последнее значение.
     *     - `SharingStarted.WhileSubscribed(5000)`: Начинает сбор данных, когда есть подписчики (UI виден),
     *       и останавливает через 5 секунд после их исчезновения для экономии ресурсов.
     */
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
            initialValue = StartDestination.Loading // Начальное значение, пока Flow не эмитит первое реальное значение
        )

    /**
     * Обрабатывает событие завершения онбординга.
     * Этот метод вызывается из UI (например, по нажатию на кнопку "Завершить" на последнем экране онбординга).
     */
    fun onOnboardingFinished() {
        viewModelScope.launch {
            finishOnboardingUseCase()
        }
    }
}


/**
 * Определяет стартовый экран на основе статуса онбординга.
 */
sealed class StartDestination {
    /**
     * Промежуточное состояние, пока идет чтение из DataStore.
     * Позволяет показать экран загрузки (Splash Screen).
     */
    object Loading : StartDestination()

    /**
     * Указывает, что нужно показать экран онбординга.
     */
    object Onboarding : StartDestination()

    /**
     * Указывает, что нужно показать главный экран приложения (Home).
     */
    object Main : StartDestination()
}
