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
    @Mock
    private lateinit var mockQuoteApiRepository: QuoteApiRepository
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun setUp() {
        getQuotesUseCase = GetQuotesUseCase(mockQuoteApiRepository)
    }
    @Test
    fun invoke_whenRepositoryReturnsData_shouldReturnSuccessResult() = runTest {
        // 1. ARRANGE
        val expectedQuotes = listOf(
            Quote(quote = "Test Цитата 1", author = "Автор 1"),
            Quote(quote = "Test Цитата 2", author = "Автор 2")
        )
        whenever(mockQuoteApiRepository.getQuoteList()).thenReturn(expectedQuotes)
        // 2. ACT
        val result = getQuotesUseCase()
        // 3. ASSERT
        assertTrue(result.isSuccess)
        assertEquals(expectedQuotes, result.getOrNull())
    }

    @Test
    fun invoke_whenRepositoryThrowsException_shouldReturnFailureResult() = runTest {
        // 1. ARRANGE
        val expectedException = RuntimeException("Network error") //тк не столь важно какой тип искл мы поймаем главное отловить
        whenever(mockQuoteApiRepository.getQuoteList()).thenThrow(expectedException)
        // 2. ACT
        val result = getQuotesUseCase()
        // 3. ASSERT
        assertTrue(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
    }


}