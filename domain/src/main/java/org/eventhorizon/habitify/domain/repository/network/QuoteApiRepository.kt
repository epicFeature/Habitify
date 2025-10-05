package org.eventhorizon.habitify.domain.repository.network

import org.eventhorizon.habitify.domain.model.network.Quote

interface QuoteApiRepository {
    suspend fun getQuoteList(): List<Quote>
}