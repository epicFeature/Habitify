package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import org.eventhorizon.habitify.domain.usecase.BaseUseCaseTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.verify

class CreateHabitUseCaseTest : BaseUseCaseTest() {
    @Mock
    private lateinit var mockHabitRepository: HabitRepository
    private lateinit var createHabitUseCase: CreateHabitUseCase

    @Before
    fun setUp() {
        createHabitUseCase = CreateHabitUseCase(mockHabitRepository)
    }

    @Test
    fun invoke_withHabit_shouldCallCreateHabitOnRepository() = runTest {
        // 1. ARRANGE
        val habitToCreate = Habit(
            id = 0, //обычно бд генерит
            name = "Ходить в спортзал",
            color = 5,
            initialDaysToFinish = 30,
            daysToFinish = 30,
            statistics = emptyList()
        )
        // 2. ACT
        createHabitUseCase(habitToCreate)
        // 3. ASSERT
        verify(mockHabitRepository).createHabit(habitToCreate)
    }
}