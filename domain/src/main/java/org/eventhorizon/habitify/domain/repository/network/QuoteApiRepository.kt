package org.eventhorizon.habitify.domain.repository.network

import org.eventhorizon.habitify.domain.model.network.QuoteList

interface QuoteApiRepository {
    suspend fun getQuoteList(): QuoteList
}