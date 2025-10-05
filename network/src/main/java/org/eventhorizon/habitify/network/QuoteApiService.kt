package org.eventhorizon.habitify.network

import org.eventhorizon.habitify.network.model.QuoteDTO
import retrofit2.http.GET

interface QuoteApiService {
    @GET("v1/quotes")
    suspend fun getQuoteListApi(): List<QuoteDTO>
}