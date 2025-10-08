package org.eventhorizon.habitify.data.repositories.database

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.data.mapper.toDomain
import org.eventhorizon.habitify.data.mapper.toEntity
import org.eventhorizon.habitify.data.repositories.BaseRepositoryTest
import org.eventhorizon.habitify.database.dao.HabitDao
import org.eventhorizon.habitify.database.entity.HabitDbEntity
import org.eventhorizon.habitify.database.entity.HabitStatDb
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.model.database.HabitStat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

class HabitRepositoryImplTest: BaseRepositoryTest() {
    @Mock
    private lateinit var habitDao: HabitDao
    private lateinit var repository: HabitRepositoryImpl

    @Before
    fun setUp() {
        repository = HabitRepositoryImpl(habitDao)
    }

    // имя метода по паттерну [Метод]_[Сценарий]_[ОжидаемыйРезультат]
    @Test
    fun getAllHabits_whenDaoReturnsEntities_shouldReturnDomainModels() = runTest {
        // 1. ARRANGE
        val today = LocalDate.now()
        val dbEntities = listOf(
            HabitDbEntity(
                id = 1,
                name = "Читать книгу",
                color = 1,
                initialDaysToFinish = 21,
                daysToFinish = 20,
                statistics = listOf(HabitStatDb(today, true))
            )
        )
        whenever(habitDao.getAllHabits()).thenReturn(flowOf(dbEntities))
        // 2. ACT
        val resultFlow = repository.getAllHabits()
        // 3. ASSERT
        val actualHabits = resultFlow.first()
        val expectedHabits = listOf(
            Habit(
                id = 1,
                name = "Читать книгу",
                color = 1,
                initialDaysToFinish = 21,
                daysToFinish = 20,
                statistics = listOf(HabitStat(today, true))
            )
        )
        assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getHabitById_whenHabitExists_shouldReturnFlowWithDomainHabit() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val today = LocalDate.now()
        val dbEntity = HabitDbEntity(
            id = habitId,
            name = "Медитировать",
            color = 3,
            initialDaysToFinish = 10,
            daysToFinish = 5,
            statistics = listOf(HabitStatDb(today.minusDays(1), true))
        )
        whenever(habitDao.getHabitById(habitId)).thenReturn(flowOf(dbEntity))
        // 2. ACT
        val actualHabitFlow = repository.getHabitById(habitId)
        // 3. ASSERT
        val expectedHabit = dbEntity.toDomain()
        assertEquals(expectedHabit, actualHabitFlow.first())
    }

    @Test
    fun getHabitById_whenHabitDoesNotExist_shouldReturnFlowWithNull() = runTest {
        // 1. ARRANGE
        val nonExistentHabitId = 999
        whenever(habitDao.getHabitById(nonExistentHabitId)).thenReturn(flowOf(null))
        // 2. ACT
        val actualHabitFlow = repository.getHabitById(nonExistentHabitId)
        // 3. ASSERT
        assertNull(actualHabitFlow.first())
    }

    @Test
    fun createHabit_withValidData_shouldCallDaoWithMappedEntity() = runTest {
        // 1. ARRANGE
        val domainHabit = Habit(
            id = 0,
            name = "Ходить в спортзал",
            color = 2,
            initialDaysToFinish = 30,
            daysToFinish = 30,
            statistics = emptyList()
        )
        val expectedDbEntity = domainHabit.toEntity()
        // 2. ACT
        repository.createHabit(domainHabit)
        // 3. ASSERT
        verify(habitDao).insertHabit(expectedDbEntity)
    }


    @Test
    fun updateHabit_withValidData_shouldCallDaoWithMappedEntity() = runTest {
        // 1. ARRANGE
        val habitToUpdate = Habit(
            id = 1,
            name = "Updated Habit Name",
            color = 5,
            initialDaysToFinish = 30,
            daysToFinish = 15,
            statistics = listOf(HabitStat(LocalDate.now(), true))
        )
        val expectedDbEntity = habitToUpdate.toEntity()
        // 2. ACT
        repository.updateHabit(habitToUpdate)
        // 3. ASSERT
        verify(habitDao).updateHabit(expectedDbEntity)
    }


    @Test
    fun deleteHabit_withValidData_shouldCallDaoWithMappedEntity() = runTest {
        // 1. ARRANGE
        val habitToDelete = Habit(
            id = 42,
            name = "Habit to be deleted",
            color = 9,
            initialDaysToFinish = 10,
            daysToFinish = 10,
            statistics = emptyList()
        )
        val expectedDbEntity = habitToDelete.toEntity()
        // 2. ACT
        repository.deleteHabit(habitToDelete)
        // 3. ASSERT
        verify(habitDao).deleteHabit(expectedDbEntity)
    }

}