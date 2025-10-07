package org.eventhorizon.habitify.data.repositories.network

import kotlinx.coroutines.test.runTest
import org.eventhorizon.habitify.data.repositories.BaseRepositoryTest
import org.eventhorizon.habitify.domain.model.network.Quote
import org.eventhorizon.habitify.network.QuoteApiService
import org.eventhorizon.habitify.network.model.QuoteDTO
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

class QuoteApiRepositoryImplTest: BaseRepositoryTest() {
    @Mock
    private lateinit var quoteApiService: QuoteApiService // 1. Мокаем зависимость - ApiService

    private lateinit var repository: QuoteApiRepositoryImpl

    @Before
    fun setUp() {
        repository = QuoteApiRepositoryImpl(quoteApiService)
    }

    @Test
    fun getQuoteList_whenApiReturnsData_shouldReturnDomainModels() = runTest {
        // 1. ARRANGE
        // Создаем тестовые DTO, которые якобы вернет наш API
        val quoteDtoList = listOf(
            QuoteDTO(
                quote = "The journey of a thousand miles begins with a single step.",
                author = "Lao Tzu",
                category = "philosophy" // Это поле должно быть проигнорировано
            ),
            QuoteDTO(
                quote = "That which does not kill us makes us stronger.",
                author = "Friedrich Nietzsche",
                category = "philosophy" // И это тоже
            )
        )

        // Обучаем мок: когда будет вызвана suspend функция getQuoteListApi(),
        // она должна вернуть наш тестовый список DTO.
        whenever(quoteApiService.getQuoteListApi()).thenReturn(quoteDtoList)

        // 2. ACT
        // Вызываем метод репозитория.
        val actualQuotes: List<Quote> = repository.getQuoteList()

        // 3. ASSERT
        // Создаем ожидаемый результат (доменные модели)
        val expectedQuotes = listOf(
            Quote(
                quote = "The journey of a thousand miles begins with a single step.",
                author = "Lao Tzu"
            ),
            Quote(
                quote = "That which does not kill us makes us stronger.",
                author = "Friedrich Nietzsche"
            )
        )

        // Можно также создать ожидаемый результат с помощью маппера,
        // чтобы тест не сломался, если вы измените сам маппер. Это даже лучше:
        // val expectedQuotes = quoteDtoList.map { it.toDomain() }

        // Сравниваем, что результат вызова (actualQuotes)
        // совпадает с ожидаемым результатом (expectedQuotes).
        assertEquals(expectedQuotes, actualQuotes)
    }

    @Test
    fun getQuoteList_whenApiReturnsEmptyList_shouldReturnEmptyList() = runTest {
        // 1. ARRANGE
        // Обучаем мок возвращать пустой список.
        whenever(quoteApiService.getQuoteListApi()).thenReturn(emptyList())

        // 2. ACT
        val actualQuotes = repository.getQuoteList()

        // 3. ASSERT
        // Проверяем, что репозиторий также вернул пустой список.
        Assert.assertEquals(emptyList<Quote>(), actualQuotes)
    }
}