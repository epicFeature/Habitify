package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import org.eventhorizon.habitify.domain.usecase.BaseUseCaseTest
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever
import java.time.LocalDate

class GetHabitByIdUseCaseTest: BaseUseCaseTest() {

    // Мокаем зависимость UseCase'а — интерфейс репозитория
    @Mock
    private lateinit var mockHabitRepository: HabitRepository

    // Тестируемый класс
    private lateinit var getHabitByIdUseCase: GetHabitByIdUseCase

    @Before
    fun setUp() {
        // Инициализируем UseCase, передавая в него наш мок
        getHabitByIdUseCase = GetHabitByIdUseCase(mockHabitRepository)
    }

    // --- Сценарий 1: Привычка с заданным ID существует ---
    @Test
    fun invoke_whenHabitExists_shouldReturnFlowWithHabit() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val today = LocalDate.now()
        // Создаем тестовую привычку, которую якобы вернет репозиторий
        val expectedHabit = Habit(
            id = habitId,
            name = "Read a book",
            color = 1,
            initialDaysToFinish = 21,
            daysToFinish = 15,
            statistics = listOf(HabitStat(today, true))
        )

        // "Обучаем" мок: когда у него вызовут getHabitById с нашим ID,
        // он должен вернуть Flow, содержащий нашу тестовую привычку.
        whenever(mockHabitRepository.getHabitById(habitId)).thenReturn(flowOf(expectedHabit))

        // 2. ACT
        // Вызываем UseCase с тем же ID
        val resultFlow = getHabitByIdUseCase(habitId)

        // 3. ASSERT
        // Собираем первое значение из Flow и проверяем, что оно совпадает с ожидаемым.
        Assert.assertEquals(expectedHabit, resultFlow.first())
    }

    // --- Сценарий 2: Привычка с заданным ID НЕ существует ---
    @Test
    fun invoke_whenHabitDoesNotExist_shouldReturnFlowWithNull() = runTest {
        // 1. ARRANGE
        val nonExistentId = 999

        // "Обучаем" мок: когда у него вызовут getHabitById с несуществующим ID,
        // он должен вернуть Flow, содержащий null.
        whenever(mockHabitRepository.getHabitById(nonExistentId)).thenReturn(flowOf(null))

        // 2. ACT
        // Вызываем UseCase с тем же несуществующим ID
        val resultFlow = getHabitByIdUseCase(nonExistentId)

        // 3. ASSERT
        // Собираем первое значение из Flow и проверяем, что оно равно null.
        assertNull(resultFlow.first())
    }
}