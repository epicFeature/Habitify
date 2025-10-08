package org.eventhorizon.habitify.domain.usecase.network

import org.eventhorizon.habitify.domain.model.network.Quote
import org.eventhorizon.habitify.domain.repository.network.QuoteApiRepository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val quoteApiRepository: QuoteApiRepository
) {
    suspend operator fun invoke(): Result<List<Quote>> {
        return try {
            val quotes = quoteApiRepository.getQuoteList()
            Result.success(quotes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}