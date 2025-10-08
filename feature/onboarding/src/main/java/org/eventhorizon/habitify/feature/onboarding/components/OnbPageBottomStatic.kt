import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.onboarding.R
import org.eventhorizon.habitify.feature.onboarding.components.OnbPagerIndicator
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.AppColor.DarkPurple
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnDisablePurpleText
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnOrange
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.congratsDialogBtnTextStyle
import org.eventhorizon.habitify.ui.components.theme.onbSkipNextTextStyle
import org.eventhorizon.habitify.ui.components.theme.onbSubtitleTextStyle

@Composable
fun OnbPageBottomStatic(
    modifier: Modifier,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    pagerState: PagerState
) {
    val colorfulSubtitleText = buildAnnotatedString {
        pushStyle(SpanStyle(color = AppColor.DarkPurple))
        append("Мы ")

        pushStyle(SpanStyle(color = AppColor.OnbTextOrange))
        append("поможем тебе ")

        pop() // возврат к предыдущему стилю
        append("стать лучшей версией ")

        pushStyle(SpanStyle(color = AppColor.OnbTextOrange))
        append("себя.")
    }


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = colorfulSubtitleText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            textAlign = TextAlign.Center,
            style = onbSubtitleTextStyle
        )
        Spacer(Modifier.height(52.dp))
        if (pagerState.currentPage != pagerState.pageCount-1){
            Box(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.onb_screen_skip),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(onClick = onSkipClick)
                        .semantics{
                            contentDescription = "Onboarding Skip Button"
                        },
                    style = onbSkipNextTextStyle
                )
                OnbPagerIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    pagerState
                )
                Text(
                    text = stringResource(R.string.onb_screen_next),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = onNextClick),
                    style = onbSkipNextTextStyle
                )
            }
        } else {
            Button(
                onClick = onSkipClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .semantics { contentDescription = "Get Started Button" },
                contentPadding = PaddingValues(vertical = 22.dp),
                shape = Shapes.small,
                colors = ButtonColors(
                    contentColor = DarkPurple,
                    containerColor = OnbBtnOrange,
                    disabledContentColor = OnbBtnDisablePurpleText,
                    disabledContainerColor = OnbBtnOrange
                ),
            ) {
                Text(
                    text = stringResource(R.string.onb_screen_get_start),
                    style = congratsDialogBtnTextStyle
                )
            }
        }
        Spacer(Modifier.height(36.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnbPageBottomStaticLastPage() {
    OnbPageBottomStatic(
        modifier = Modifier,
        onSkipClick = {},
        onNextClick = {},
        pagerState = PagerState(currentPage = 2, pageCount = {3}))
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnbPageBottomStatic() {
    OnbPageBottomStatic(
        modifier = Modifier,
        onSkipClick = {},
        onNextClick = {},
        pagerState = PagerState { 3 })
}