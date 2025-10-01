package org.eventhorizon.habitify.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.R
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.homeQuoteAuthorTextStyle
import org.eventhorizon.habitify.ui.components.theme.homeQuoteTextStyle

@Composable
fun QuoteCard(modifier: Modifier = Modifier, quoteText: String, quoteAuthor: String) {
    Row(
        modifier = modifier
            .background(AppColor.White, Shapes.medium)
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 18.dp, top = 18.dp, bottom = 18.dp, end = 18.dp)
                .wrapContentSize()
        ) {
            Text(
                text = quoteText,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = homeQuoteTextStyle
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "- ${quoteAuthor.uppercase()}",
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = homeQuoteAuthorTextStyle
            )
        }
        Image(
            painter = painterResource(id = R.drawable.pic_quote_bg),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewQuoteCard() {
    QuoteCard(
        quoteText = "We first make our habits, \n" +
                "and then our habits \n" +
                "makes us.",
        quoteAuthor = "anonymous"
    )
}