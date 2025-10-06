package org.eventhorizon.habitify.data.mapper

import org.eventhorizon.habitify.domain.model.network.Quote
import org.eventhorizon.habitify.network.model.QuoteDTO

fun QuoteDTO.toDomain(): Quote {
    return Quote(
        quote = quote,
        author = author
    )
}