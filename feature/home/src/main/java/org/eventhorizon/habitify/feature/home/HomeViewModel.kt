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
    //private val updateHabitUseCase: UpdateHabitUseCase,
    private val updateHabitProgressUseCase: UpdateHabitProgressUseCase,
    private val getQuotesUseCase: GetQuotesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeContract.HomeUiState.State())
    val uiState = _uiState.asStateFlow()

    // 1. Создаем приватный StateFlow для хранения доменных моделей
    private val _domainHabits = MutableStateFlow<List<Habit>>(emptyList())

    private val _effect = Channel<HomeContract.HomeUiEffect>()
    val effect = _effect.receiveAsFlow()

    private var checkClickJob: Job? = null // Для предотвращения многократных нажатий

    private val today = LocalDate.now()
    // Создаем список дат для шапки чарта
    private val dateRange = (0..4L).map { today.minusDays(it) }.reversed()

    init {
        loadQuote()
        prepareChartDays()
        observeHabits()
        // 2. Добавляем новую подписку, которая будет преобразовывать доменные модели в UI
        observeAndMapDomainToUi()
    }

    fun setEvent(event: HomeContract.HomeUiEvent) {
        when (event) {
            is HomeContract.HomeUiEvent.OnHabitCheckClick -> onHabitCheckClicked(event.habitId, event.date, event.isChecked)
            is HomeContract.HomeUiEvent.OnHabitCardClick -> onHabitCardClicked(event.habitId)
            HomeContract.HomeUiEvent.OnAddHabitClick -> onAddHabitClicked()
            HomeContract.HomeUiEvent.OnCongratulationsDialogContinue -> {
                _uiState.update { it.copy(showCongratulationsDialog = false) }
            }
            HomeContract.HomeUiEvent.OnCongratulationsDialogCreateNew -> {
                _uiState.update { it.copy(showCongratulationsDialog = false) }
                onAddHabitClicked() // Используем уже существующую логику навигации
            }
        }
    }

    // --- НАЧАЛО: СКОРРЕКТИРОВАННЫЙ МЕТОД ---
    private fun loadQuote() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingQuote = true) }

            // Вызываем use case, который возвращает Result<QuoteList>
            val result = getQuotesUseCase()

            result.onSuccess { quoteList ->
                // Если результат успешный, берем случайную цитату из списка
                val randomQuote = quoteList.firstOrNull()
                _uiState.update {
                    it.copy(
                        isLoadingQuote = false,
                        // Если список не пустой, используем данные, иначе - запасной вариант
                        quoteText = randomQuote?.quote ?: "Your life does not get better by chance, it gets better by change.",
                        quoteAuthor = randomQuote?.author ?: "Jim Rohn"
                    )
                }
            }.onFailure { exception ->
                // Если произошла ошибка (например, нет интернета),
                // обрабатываем ее здесь. Можно залогировать exception.
                // PlutoLog.e("QuoteApi", "Failed to load quotes", exception)
                _uiState.update {
                    it.copy(
                        isLoadingQuote = false,
                        quoteText = "Your life does not get better by chance, it gets better by change.", // Запасной вариант
                        quoteAuthor = "Jim Rohn"
                    )
                }
            }
        }
    }
    // --- КОНЕЦ: СКОРРЕКТИРОВАННЫЙ МЕТОД ---

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

    private fun observeHabits() {
        // 3. Этот метод теперь ТОЛЬКО получает данные и кладет их в _domainHabits
        getAllHabitsUseCase()
            .onEach { habits ->
                _domainHabits.value = habits
            }
            .launchIn(viewModelScope)
    }

    private fun observeAndMapDomainToUi() {
        // 4. Этот метод слушает _domainHabits и обновляет UI
        _domainHabits.onEach { allHabits ->
            val habitsForChart = allHabits.map { habit ->
                HomeContract.HomeUiState.State.HabitForChart(
                    id = habit.id,
                    name = habit.name,
                    color = habit.color,
                    // Для каждой привычки создаем список "отметок" для нужного диапазона дат
                    checks = dateRange.map { dateInChart ->
                        // Ищем статистику для конкретного дня в полном списке статистики привычки
                        val statForDate = habit.statistics.find { it.day.isEqual(dateInChart) }
                        HomeContract.HomeUiState.State.HabitCheck(
                            date = dateInChart,
                            isChecked = statForDate?.isDone ?: false,
                            // Кликать можно только по сегодняшнему дню
                            isClickable = dateInChart.isEqual(today)
                        )
                    }
                )
            }
            _uiState.update { it.copy(habits = habitsForChart) }
        }.launchIn(viewModelScope)
    }

    // --- НАЧАЛО: СКОРРЕКТИРОВАННЫЙ МЕТОД ---
    /*private fun observeHabits() {
        // Вызываем use case, который вернет Flow<List<Habit>>
        getAllHabitsUseCase().onEach { allHabits ->
            // Преобразуем каждую доменную модель Habit в UI-модель HabitForChart
            val habitsForChart = allHabits.map { habit ->
                HomeContract.HomeUiState.State.HabitForChart(
                    id = habit.id,
                    name = habit.name,
                    color = habit.color,
                    // Для каждой привычки создаем список "отметок" для нужного диапазона дат
                    checks = dateRange.map { dateInChart ->
                        // Ищем статистику для конкретного дня в полном списке статистики привычки
                        val statForDate = habit.statistics.find { it.day.isEqual(dateInChart) }
                        HomeContract.HomeUiState.State.HabitCheck(
                            date = dateInChart,
                            isChecked = statForDate?.isDone ?: false,
                            // Кликать можно только по сегодняшнему дню
                            isClickable = dateInChart.isEqual(today)
                        )
                    }
                )
            }
            // Обновляем состояние UI
            _uiState.update { it.copy(habits = habitsForChart) }
        }.launchIn(viewModelScope) // Запускаем сбор Flow в viewModelScope
    }*/
    // --- КОНЕЦ: СКОРРЕКТИРОВАННЫЙ МЕТОД ---


    private fun onHabitCheckClicked(habitId: Int, date: LocalDate, isChecked: Boolean) {
        if (checkClickJob?.isActive == true) return

        checkClickJob = viewModelScope.launch {
            // Вызываем наш новый UseCase
            val isHabitFinished = updateHabitProgressUseCase(habitId, date, isChecked)

            // Если он вернул true, показываем диалог
            if (isHabitFinished) {
                _uiState.update { it.copy(showCongratulationsDialog = true) }
            }
        }
    }

/*    private fun onHabitCheckClicked(habitId: Int, date: LocalDate, isChecked: Boolean) {
        if (checkClickJob?.isActive == true) return

        checkClickJob = viewModelScope.launch {
            // 1. Получаем оригинальную привычку из нашего внутреннего состояния
            val originalHabit = _domainHabits.value.find { it.id == habitId } ?: return@launch

            // 2. Обновляем или добавляем статистику
            val updatedStats = originalHabit.statistics.toMutableList()
            val statIndex = updatedStats.indexOfFirst { it.day.isEqual(date) }

            if (statIndex != -1) {
                // Статистика есть - обновляем
                updatedStats[statIndex] = updatedStats[statIndex].copy(isDone = !isChecked)
            } else {
                // Статистики нет - добавляем
                updatedStats.add(HabitStat(day = date, isDone = !isChecked))
            }

            // 3. Создаем обновленную модель
            val updatedHabit = originalHabit.copy(statistics = updatedStats)

            // 4. Вызываем use case
            updateHabitUseCase(updatedHabit)
        }
    }*/

    /*private fun onHabitCheckClicked(habitId: Int, date: LocalDate, isChecked: Boolean) {
        // Защита от многократных нажатий
        if (checkClickJob?.isActive == true) return

        checkClickJob = viewModelScope.launch {
            // 1. Находим текущую привычку в состоянии UI
            val currentHabit = uiState.value.habits.find { it.id == habitId } ?: return@launch

            // 2. Находим соответствующую доменную модель привычки, чтобы получить полную статистику
            // Это необходимо, так как UI-модель хранит статистику только за 5 дней
            val originalHabitDomainModel = getAllHabitsUseCase().first().find { it.id == habitId } ?: return@launch

            // 3. Обновляем или добавляем статистику для нужной даты
            val updatedStats = originalHabitDomainModel.statistics.toMutableList()
            val statIndex = updatedStats.indexOfFirst { it.day.isEqual(date) }

            if (statIndex != -1) {
                // Если статистика для этого дня уже есть, обновляем ее
                updatedStats[statIndex] = updatedStats[statIndex].copy(isDone = !isChecked)
            } else {
                // Если нет, добавляем новую запись
                updatedStats.add(HabitStat(day = date, isDone = !isChecked))
            }

            // 4. Создаем обновленную доменную модель Habit
            val updatedHabit = originalHabitDomainModel.copy(
                statistics = updatedStats
            )

            // 5. Вызываем use case с полным, обновленным объектом
            updateHabitUseCase(updatedHabit)
        }
    }*/

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