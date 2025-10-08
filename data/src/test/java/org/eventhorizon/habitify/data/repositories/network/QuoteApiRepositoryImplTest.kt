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
    private lateinit var quoteApiService: QuoteApiService
    private lateinit var repository: QuoteApiRepositoryImpl
    @Before
    fun setUp() {
        repository = QuoteApiRepositoryImpl(quoteApiService)
    }

    @Test
    fun getQuoteList_whenApiReturnsData_shouldReturnDomainModels() = runTest {
        // 1. ARRANGE
        val quoteDtoList = listOf(
            QuoteDTO(
                quote = "Путь в тысячу ли начинается с первого шага.",
                author = "Лао-цзы",
                category = "philosophy"
            ),
            QuoteDTO(
                quote = "То, что нас не убивает, делает нас сильнее.",
                author = "Фридрих Ницше",
                category = "philosophy"
            )
        )
        whenever(quoteApiService.getQuoteListApi()).thenReturn(quoteDtoList)
        // 2. ACT
        val actualQuotes: List<Quote> = repository.getQuoteList()
        // 3. ASSERT
        val expectedQuotes = listOf(
            Quote(
                quote = "Путь в тысячу ли начинается с первого шага.",
                author = "Лао-цзы"
            ),
            Quote(
                quote = "То, что нас не убивает, делает нас сильнее.",
                author = "Фридрих Ницше"
            )
        )
        assertEquals(expectedQuotes, actualQuotes)
    }

    @Test
    fun getQuoteList_whenApiReturnsEmptyList_shouldReturnEmptyList() = runTest {
        // 1. ARRANGE
        whenever(quoteApiService.getQuoteListApi()).thenReturn(emptyList())
        // 2. ACT
        val actualQuotes = repository.getQuoteList()
        // 3. ASSERT
        Assert.assertEquals(emptyList<Quote>(), actualQuotes)
    }
}