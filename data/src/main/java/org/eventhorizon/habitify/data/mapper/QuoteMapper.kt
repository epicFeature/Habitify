package org.eventhorizon.habitify.data.mapper

import org.eventhorizon.habitify.domain.model.network.Quote
import org.eventhorizon.habitify.domain.model.network.QuoteList
import org.eventhorizon.habitify.network.model.QuoteDTO
import org.eventhorizon.habitify.network.model.QuoteListDTO

fun QuoteDTO.toDomain(): Quote {
    return Quote(
        quote = quote,
        author = author
    )
}

fun QuoteListDTO.toDomain(): QuoteList { //но нужен всегда только первый элемент
    return QuoteList(
        quoteListDTO = quoteListDTO.map { it.toDomain() }
    )
}