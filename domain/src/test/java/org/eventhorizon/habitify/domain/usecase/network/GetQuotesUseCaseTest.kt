package org.eventhorizon.habitify.domain.usecase.network

import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.domain.model.network.Quote
import org.eventhorizon.habitify.domain.repository.network.QuoteApiRepository
import org.eventhorizon.habitify.domain.usecase.BaseUseCaseTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

class GetQuotesUseCaseTest: BaseUseCaseTest() {
    // Мокаем зависимость UseCase'а — интерфейс репозитория
    @Mock
    private lateinit var mockQuoteApiRepository: QuoteApiRepository

    // Тестируемый класс
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun setUp() {
        // Инициализируем UseCase, передавая в него наш мок
        getQuotesUseCase = GetQuotesUseCase(mockQuoteApiRepository)
    }

    // --- Сценарий 1: Успешное получение данных ---
    @Test
    fun invoke_whenRepositoryReturnsData_shouldReturnSuccessResult() = runTest {
        // 1. ARRANGE
        // Создаем тестовые данные, которые якобы вернет наш мок-репозиторий
        val expectedQuotes = listOf(
            Quote(quote = "Test Quote 1", author = "Author 1"),
            Quote(quote = "Test Quote 2", author = "Author 2")
        )

        // "Обучаем" мок: когда у него будет вызван метод getQuoteList(),
        // он должен вернуть наш заранее подготовленный список.
        whenever(mockQuoteApiRepository.getQuoteList()).thenReturn(expectedQuotes)

        // 2. ACT
        // Вызываем наш UseCase
        val result = getQuotesUseCase()

        // 3. ASSERT
        // Проверяем, что результат является успешным
        assertTrue(result.isSuccess)
        // Проверяем, что данные внутри успешного результата совпадают с ожидаемыми
        assertEquals(expectedQuotes, result.getOrNull())
    }

    // --- Сценарий 2: Репозиторий выбрасывает исключение ---
    @Test
    fun invoke_whenRepositoryThrowsException_shouldReturnFailureResult() = runTest {
        // 1. ARRANGE
        // Создаем тестовое исключение, которое якобы выбросит наш репозиторий
        val expectedException = RuntimeException("Network error") //тк не столь важно какой тип искл мы поймаем главное отловить

        // "Обучаем" мок: когда у него будет вызван метод getQuoteList(),
        // он должен выбросить (throws) это исключение.
        whenever(mockQuoteApiRepository.getQuoteList()).thenThrow(expectedException)

        // 2. ACT
        // Вызываем наш UseCase
        val result = getQuotesUseCase()

        // 3. ASSERT
        // Проверяем, что результат является провальным
        assertTrue(result.isFailure)
        // Проверяем, что исключение внутри провального результата — это именно то исключение,
        // которое мы "выбросили" из репозитория.
        assertEquals(expectedException, result.exceptionOrNull())
    }


}