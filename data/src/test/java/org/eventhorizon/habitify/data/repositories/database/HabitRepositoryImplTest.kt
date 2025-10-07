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

    // @Mock создаст заглушку для нашего DAO.
    // Нам не нужна реальная база данных, мы просто скажем этому моку, как себя вести.
    @Mock
    private lateinit var habitDao: HabitDao

    // Тестируемый класс (Subject Under Test).
    private lateinit var repository: HabitRepositoryImpl

    // Метод @Before выполняется перед каждым тестом.
    // Идеальное место для инициализации тестируемого объекта.
    @Before
    fun setUp() {
        repository = HabitRepositoryImpl(habitDao)
    }

    // Имя теста должно четко описывать, что он проверяет:
    // [Метод]_[Сценарий]_[ОжидаемыйРезультат]
    @Test
    fun getAllHabits_whenDaoReturnsEntities_shouldReturnDomainModels() = runTest {
        // 1. ARRANGE (Подготовка)
        // Создаем тестовые данные, которые якобы вернет наш DAO.
        val today = LocalDate.now()
        val dbEntities = listOf(
            HabitDbEntity(
                id = 1,
                name = "Read a book",
                color = 1,
                initialDaysToFinish = 21,
                daysToFinish = 20,
                statistics = listOf(HabitStatDb(today, true))
            )
        )

        // "Обучаем" наш мок: когда будет вызван метод getAllHabits(),
        // он должен вернуть заранее подготовленный Flow с нашими тестовыми данными.
        whenever(habitDao.getAllHabits()).thenReturn(flowOf(dbEntities))

        // 2. ACT (Действие)
        // Вызываем метод, который хотим протестировать.
        val resultFlow = repository.getAllHabits()

        // 3. ASSERT (Проверка)
        // Собираем первое значение из Flow.
        val actualHabits = resultFlow.first()
        // Создаем ожидаемый результат вручную, чтобы быть уверенными в маппинге.
        val expectedHabits = listOf(
            Habit(
                id = 1,
                name = "Read a book",
                color = 1,
                initialDaysToFinish = 21,
                daysToFinish = 20,
                statistics = listOf(HabitStat(today, true))
            )
        )
        // Сравниваем, что результат работы репозитория совпадает с ожидаемым.
        assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getHabitById_whenHabitExists_shouldReturnFlowWithDomainHabit() = runTest {
        // 1. ARRANGE
        val habitId = 42
        val today = LocalDate.now()
        val dbEntity = HabitDbEntity( // Создаем не-null сущность
            id = habitId,
            name = "Meditate",
            color = 3,
            initialDaysToFinish = 10,
            daysToFinish = 5,
            statistics = listOf(HabitStatDb(today.minusDays(1), true))
        )
        // Обучаем мок: при вызове getHabitById он вернет Flow с нашей сущностью.
        whenever(habitDao.getHabitById(habitId)).thenReturn(flowOf(dbEntity))

        // 2. ACT
        // Вызываем метод, который возвращает Flow<Habit?>.
        val actualHabitFlow = repository.getHabitById(habitId)

        // 3. ASSERT
        val expectedHabit = dbEntity.toDomain()
        // Собираем первое значение из потока и сравниваем с ожидаемым объектом.
        assertEquals(expectedHabit, actualHabitFlow.first())
    }


    // --- Сценарий 2: Привычка НЕ найдена ---
    @Test
    fun getHabitById_whenHabitDoesNotExist_shouldReturnFlowWithNull() = runTest {
        // 1. ARRANGE
        val nonExistentHabitId = 999
        // Обучаем мок: при вызове getHabitById он вернет Flow, содержащий null.
        // Это имитирует поведение DAO, когда запись не найдена.
        whenever(habitDao.getHabitById(nonExistentHabitId)).thenReturn(flowOf(null))

        // 2. ACT
        // Вызываем метод, который возвращает Flow<Habit?>.
        val actualHabitFlow = repository.getHabitById(nonExistentHabitId)

        // 3. ASSERT
        // Собираем первое значение из потока и проверяем, что оно равно null.
        // Для этого используем специальный ассерт assertNull.
        assertNull(actualHabitFlow.first())
    }

    @Test
    fun createHabit_withValidData_shouldCallDaoWithMappedEntity() = runTest {
        // 1. ARRANGE
        // Создаем доменную модель, которую мы якобы передаем в репозиторий.
        val domainHabit = Habit(
            id = 0, // id=0, так как это новая привычка
            name = "Go to gym",
            color = 2,
            initialDaysToFinish = 30,
            daysToFinish = 30,
            statistics = emptyList()
        )
        // Создаем Entity-модель, которую DAO должен получить после маппинга.
        val expectedDbEntity = domainHabit.toEntity() // Используем реальный маппер

        // 2. ACT
        // Вызываем тестируемый метод.
        repository.createHabit(domainHabit)

        // 3. ASSERT
        // Проверяем (verify), что у нашего мока habitDao был вызван метод insertHabit
        // и что в качестве аргумента ему была передана именно та сущность, которую мы ожидали.
        verify(habitDao).insertHabit(expectedDbEntity)
    }


    @Test
    fun updateHabit_withValidData_shouldCallDaoWithMappedEntity() = runTest {
        // 1. ARRANGE
        // Создаем доменную модель с обновленными данными.
        val habitToUpdate = Habit(
            id = 1, // Важно, что id уже существует
            name = "Updated Habit Name",
            color = 5,
            initialDaysToFinish = 30,
            daysToFinish = 15,
            statistics = listOf(HabitStat(LocalDate.now(), true))
        )
        // Создаем ожидаемую DbEntity, которую DAO должен получить.
        val expectedDbEntity = habitToUpdate.toEntity()

        // 2. ACT
        // Вызываем метод updateHabit.
        repository.updateHabit(habitToUpdate)

        // 3. ASSERT
        // Проверяем, что у habitDao был вызван метод updateHabit,
        // и что ему была передана корректно смапленная сущность.
        verify(habitDao).updateHabit(expectedDbEntity)
    }


    @Test
    fun deleteHabit_withValidData_shouldCallDaoWithMappedEntity() = runTest {
        // 1. ARRANGE
        // Создаем доменную модель привычки, которую хотим удалить.
        val habitToDelete = Habit(
            id = 42,
            name = "Habit to be deleted",
            color = 9,
            initialDaysToFinish = 10,
            daysToFinish = 10,
            statistics = emptyList()
        )
        // Создаем ожидаемую DbEntity.
        val expectedDbEntity = habitToDelete.toEntity()

        // 2. ACT
        // Вызываем метод deleteHabit.
        repository.deleteHabit(habitToDelete)

        // 3. ASSERT
        // Проверяем, что у habitDao был вызван метод deleteHabit,
        // и ему была передана корректно смапленная сущность.
        verify(habitDao).deleteHabit(expectedDbEntity)
    }

}