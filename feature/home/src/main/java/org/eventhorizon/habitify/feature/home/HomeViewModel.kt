package org.eventhorizon.habitify.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.usecase.database.GetAllHabitsUseCase
import org.eventhorizon.habitify.domain.usecase.database.UpdateHabitProgressUseCase
import org.eventhorizon.habitify.domain.usecase.network.GetQuotesUseCase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllHabitsUseCase: GetAllHabitsUseCase,
    private val updateHabitProgressUseCase: UpdateHabitProgressUseCase,
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeContract.HomeUiState.State())
    val uiState = _uiState.asStateFlow()

    private val _domainHabits =
        MutableStateFlow<List<Habit>>(emptyList()) //todo продумать насколько оправдан приватный stateflow для хранения доменной модели

    private val _effect = Channel<HomeContract.HomeUiEffect>()
    val effect = _effect.receiveAsFlow()

    private var checkClickJob: Job? = null // для предотвращения многократных нажатий на checkbox

    private val today = LocalDate.now()
    private val dateRange = (0..4L).map { today.minusDays(it) }.reversed() //даты для шапки

    init {
        loadQuote()
        prepareChartDays()
        observeHabits()
        observeAndMapDomainToUi()
    }

    fun setEvent(event: HomeContract.HomeUiEvent) {
        when (event) {
            is HomeContract.HomeUiEvent.OnHabitCheckClick -> onHabitCheckClicked(
                event.habitId,
                event.date,
                event.isChecked
            )

            is HomeContract.HomeUiEvent.OnHabitCardClick -> onHabitCardClicked(event.habitId)
            HomeContract.HomeUiEvent.OnAddHabitClick -> onAddHabitClicked()
            HomeContract.HomeUiEvent.OnCongratulationsDialogContinue -> {
                _uiState.update { it.copy(showCongratulationsDialog = false) }
            }

            HomeContract.HomeUiEvent.OnCongratulationsDialogCreateNew -> {
                _uiState.update { it.copy(showCongratulationsDialog = false) }
                onAddHabitClicked()
            }

            HomeContract.HomeUiEvent.OnShowCongratsDialog -> {
                _uiState.update { it.copy(showCongratulationsDialog = true) }
            }
        }
    }

    private fun loadQuote() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingQuote = true) }
            val result = getQuotesUseCase()
            result.onSuccess { quoteList ->
                val randomQuote = quoteList.firstOrNull()
                _uiState.update {
                    it.copy(
                        isLoadingQuote = false,
                        quoteText = randomQuote?.quote
                            ?: "Ваша жизнь становится лучше не по воле случая, а в результате изменений.",
                        quoteAuthor = randomQuote?.author ?: "Джим Рон"
                    )
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        isLoadingQuote = false,
                        quoteText = "Ваша жизнь становится лучше не по воле случая, а в результате изменений.", // Запасной вариант
                        quoteAuthor = "Джим Рон"
                    )
                }
            }
        }
    }

    private fun prepareChartDays() {
        val dayOfMonthFormatter = DateTimeFormatter.ofPattern("dd")
        val dayOfWeekFormatter = DateTimeFormatter.ofPattern("E", Locale("ru"))

        val chartDays = dateRange.map { date ->
            HomeContract.HomeUiState.State.ChartDay(
                dayOfMonth = date.format(dayOfMonthFormatter),
                dayOfWeek = date.format(dayOfWeekFormatter).uppercase(),
                isToday = date.isEqual(today)
            )
        }
        _uiState.update { it.copy(chartDays = chartDays) }
    }

    private fun observeHabits() { //только получ данные и кладет их в _domainHabits
        getAllHabitsUseCase()
            .onEach { habits ->
                _domainHabits.value = habits
            }
            .launchIn(viewModelScope)
    }

    private fun observeAndMapDomainToUi() { //слушает _domainHabits и обновляет state
        _domainHabits.onEach { allHabits ->
            val habitsForChart = allHabits.map { habit ->
                HomeContract.HomeUiState.State.HabitForChart(
                    id = habit.id,
                    name = habit.name,
                    color = habit.color,
                    checks = dateRange.map { dateInChart ->
                        val statForDate =
                            habit.statistics.find { it.day.isEqual(dateInChart) } //для конкретного дня
                        HomeContract.HomeUiState.State.HabitCheck(
                            date = dateInChart,
                            isChecked = statForDate?.isDone ?: false,
                            isClickable = dateInChart.isEqual(today)
                        )
                    }
                )
            }
            _uiState.update { it.copy(habits = habitsForChart) }
        }.launchIn(viewModelScope)
    }

    private fun onHabitCheckClicked(habitId: Int, date: LocalDate, isChecked: Boolean) {
        if (checkClickJob?.isActive == true) return
        checkClickJob = viewModelScope.launch {
            val isHabitFinished = updateHabitProgressUseCase(habitId, date, isChecked)
            if (isHabitFinished) {
                _uiState.update { it.copy(showCongratulationsDialog = true) }
            }
        }
    }

    private fun onHabitCardClicked(habitId: Int) {
        viewModelScope.launch {
            _effect.send(HomeContract.HomeUiEffect.NavigateToHabitInfo(habitId.toString()))
        }
    }

    private fun onAddHabitClicked() {
        viewModelScope.launch {
            _effect.send(HomeContract.HomeUiEffect.NavigateToNewHabit)
        }
    }
}