package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import org.eventhorizon.habitify.domain.usecase.BaseUseCaseTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.verify

class CreateHabitUseCaseTest: BaseUseCaseTest() {
        // Мокаем зависимость UseCase'а — интерфейс репозитория
    @Mock
    private lateinit var mockHabitRepository: HabitRepository

    // Тестируемый класс
    private lateinit var createHabitUseCase: CreateHabitUseCase

    @Before
    fun setUp() {
        // Инициализируем UseCase, передавая в него наш мок
        createHabitUseCase = CreateHabitUseCase(mockHabitRepository)
    }

    @Test
    fun invoke_withHabit_shouldCallCreateHabitOnRepository() = runTest {
        // 1. ARRANGE
        // Создаем тестовый объект Habit, который мы "передадим" в UseCase
        val habitToCreate = Habit(
            id = 0, // id обычно генерируется базой данных, поэтому 0
            name = "Go to the gym",
            color = 5,
            initialDaysToFinish = 30,
            daysToFinish = 30,
            statistics = emptyList()
        )

        // 2. ACT
        // Вызываем наш UseCase с тестовым объектом
        createHabitUseCase(habitToCreate)

        // 3. ASSERT
        // Проверяем (verify), что у мок-репозитория был вызван метод createHabit,
        // и что ему был передан в точности тот же объект `habitToCreate`,
        // который мы передали в UseCase.
        verify(mockHabitRepository).createHabit(habitToCreate)
    }
}