package org.eventhorizon.habitify.network

import org.eventhorizon.habitify.network.model.QuoteListDTO
import retrofit2.http.GET

interface QuoteApiService {
    @GET("v1/quotes")
    suspend fun getQuoteListApi(): QuoteListDTO
}