package org.eventhorizon.habitify.feature.habitinfo


import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.domain.usecase.database.DeleteHabitUseCase
import org.eventhorizon.habitify.domain.usecase.database.GetHabitByIdUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

@ExperimentalCoroutinesApi
class HabitInfoViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule() // Наше новое правило для корутин!

    @Mock
    private lateinit var getHabitByIdUseCase: GetHabitByIdUseCase

    @Mock
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    private lateinit var viewModel: HabitInfoViewModel

    // --- Сценарий 1: Успешная загрузка привычки при инициализации ---
    @Test
    fun init_whenHabitExists_shouldUpdateStateWithHabitDetails() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val today = LocalDate.now()
        val habit = Habit(
            id = habitId,
            name = "Read a book",
            color = 12345,
            initialDaysToFinish = 21,
            daysToFinish = 15,
            statistics = listOf(HabitStat(today, true), HabitStat(today.minusDays(1), false))
        )
        // Обучаем UseCase возвращать наш тестовый Flow
        whenever(getHabitByIdUseCase(habitId)).thenReturn(flowOf(habit))

        // 2. ACT
        // Инициализируем ViewModel с SavedStateHandle, содержащим наш ID
        viewModel = HabitInfoViewModel(
            getHabitByIdUseCase,
            deleteHabitUseCase,
            SavedStateHandle(mapOf("habitId" to habitId.toString()))
        )

        // 3. ASSERT
        viewModel.state.test {
            val loadedState = awaitItem() // Получаем последнее состояние после загрузки
            assertFalse(loadedState.isLoading)
            assertEquals("Read a book", loadedState.habitName)
            assertEquals(15, loadedState.daysLeft)
            // Проверяем, что в markedDates попала только выполненная статистика
            assertEquals(setOf(today), loadedState.markedDates)
        }
    }

    // --- Сценарий 2: Привычка не найдена при инициализации ---
    @Test
    fun init_whenHabitDoesNotExist_shouldSendNavigateBackEffect() = runTest {
        // 1. ARRANGE
        val nonExistentId = 999
        // Обучаем UseCase возвращать null в потоке
        whenever(getHabitByIdUseCase(nonExistentId)).thenReturn(flowOf(null))

        // 2. ACT
        viewModel = HabitInfoViewModel(
            getHabitByIdUseCase,
            deleteHabitUseCase,
            SavedStateHandle(mapOf("habitId" to nonExistentId.toString()))
        )

        // 3. ASSERT
        // Проверяем, что ViewModel отправила эффект навигации назад
        viewModel.effect.test {
            assertEquals(HabitInfoContract.HabitInfoEffect.NavigateBackToHome, awaitItem())
        }
    }

    // --- Сценарий 3: Нажатие на кнопку удаления ---
    @Test
    fun `setEvent OnDeleteClick_shouldCallDeleteUseCaseAndSendNavigateBackEffect`() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val habit = Habit(habitId, "Habit to delete", 1, 10, 10, emptyList())
        whenever(getHabitByIdUseCase(habitId)).thenReturn(flowOf(habit))

        viewModel = HabitInfoViewModel(
            getHabitByIdUseCase,
            deleteHabitUseCase,
            SavedStateHandle(mapOf("habitId" to habitId.toString()))
        )

        // Пропускаем начальное состояние, чтобы убедиться, что оно загружено
        viewModel.state.test { awaitItem() }

        // 2. ACT
        // Отправляем событие удаления
        viewModel.setEvent(HabitInfoContract.HabitInfoEvent.OnDeleteClick)

        // 3. ASSERT
        // Проверяем, что был вызван UseCase с правильным объектом
        verify(deleteHabitUseCase).invoke(habit)
        // Проверяем, что был отправлен эффект навигации назад
        viewModel.effect.test {
            assertEquals(HabitInfoContract.HabitInfoEffect.NavigateBackToHome, awaitItem())
        }
    }

    // --- Сценарий 4: Нажатие на кнопку завершения ---
    @Test
    fun `setEvent OnCompleteClick_shouldCallDeleteUseCaseAndSendNavigateBackWithCompletionEffect`() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val habit = Habit(habitId, "Habit to complete", 1, 10, 10, emptyList())
        whenever(getHabitByIdUseCase(habitId)).thenReturn(flowOf(habit))
        viewModel = HabitInfoViewModel(
            getHabitByIdUseCase,
            deleteHabitUseCase,
            SavedStateHandle(mapOf("habitId" to habitId.toString()))
        )
        viewModel.state.test { awaitItem() } // Убеждаемся, что привычка загружена

        // 2. ACT
        viewModel.setEvent(HabitInfoContract.HabitInfoEvent.OnCompleteClick)

        // 3. ASSERT
        verify(deleteHabitUseCase).invoke(habit)
        viewModel.effect.test {
            val expectedEffect = HabitInfoContract.HabitInfoEffect.NavigateBackWithCompletion(showCongrats = true)
            assertEquals(expectedEffect, awaitItem())
        }
    }
}