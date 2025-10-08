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
    @Mock
    private lateinit var mockHabitRepository: HabitRepository
    private lateinit var getHabitByIdUseCase: GetHabitByIdUseCase
    @Before
    fun setUp() {
        getHabitByIdUseCase = GetHabitByIdUseCase(mockHabitRepository)
    }

    @Test
    fun invoke_whenHabitExists_shouldReturnFlowWithHabit() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val today = LocalDate.now()
        val expectedHabit = Habit(
            id = habitId,
            name = "Читать книги",
            color = 1,
            initialDaysToFinish = 21,
            daysToFinish = 15,
            statistics = listOf(HabitStat(today, true))
        )
        whenever(mockHabitRepository.getHabitById(habitId)).thenReturn(flowOf(expectedHabit))
        // 2. ACT
        val resultFlow = getHabitByIdUseCase(habitId)
        // 3. ASSERT
        Assert.assertEquals(expectedHabit, resultFlow.first())
    }

    @Test
    fun invoke_whenHabitDoesNotExist_shouldReturnFlowWithNull() = runTest {
        // 1. ARRANGE
        val nonExistentId = 999
        whenever(mockHabitRepository.getHabitById(nonExistentId)).thenReturn(flowOf(null))
        // 2. ACT
        val resultFlow = getHabitByIdUseCase(nonExistentId)
        // 3. ASSERT
        assertNull(resultFlow.first())
    }
}