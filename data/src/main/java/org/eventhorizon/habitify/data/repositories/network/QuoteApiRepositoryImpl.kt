package org.eventhorizon.habitify.data.repositories.network

import org.eventhorizon.habitify.data.mapper.toDomain
import org.eventhorizon.habitify.domain.model.network.Quote
import org.eventhorizon.habitify.domain.repository.network.QuoteApiRepository
import org.eventhorizon.habitify.network.QuoteApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QuoteApiRepositoryImpl @Inject constructor(
    private val quoteApiService: QuoteApiService
) : QuoteApiRepository {

    override suspend fun getQuoteList(): List<Quote> {
        val quoteListDto = quoteApiService.getQuoteListApi()
        return quoteListDto.map { it.toDomain() }
    }
}