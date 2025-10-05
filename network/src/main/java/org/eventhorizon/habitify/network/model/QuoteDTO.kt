package org.eventhorizon.habitify.network.model

import com.google.gson.annotations.SerializedName

data class QuoteDTO(
    @SerializedName("quote")
    val quote: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("category")
    val category: String
)