package org.eventhorizon.habitify.feature.onboarding

import OnbPageBasicTopDynamic
import OnbPageBottomStatic
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.eventhorizon.habitify.feature.onboarding.model.OnbPageData
import org.eventhorizon.habitify.ui.components.theme.AppColor

@Composable
fun OnboardingScreen(modifier: Modifier=Modifier, onOnboardingFinished: () -> Unit) {
    val onbPageDataList = listOf(
        OnbPageData(
            titleRes = R.string.onb_screen_title_3,
            imageRes = R.drawable.bg_onb_3
        ),
        OnbPageData(
            titleRes = R.string.onb_screen_title_4,
            imageRes = R.drawable.bg_onb_4
        ),
        OnbPageData(
            titleRes = R.string.onb_screen_title_5,
            imageRes = R.drawable.bg_onb_5
        )
    )

    val pagerState = rememberPagerState(pageCount = { onbPageDataList.size })
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxHeight().background(color = AppColor.OnbBgWhite)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.Top
        ) { page ->
            OnbPageBasicTopDynamic(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 24.dp),
                titleRes = onbPageDataList[page].titleRes,
                imageRes = onbPageDataList[page].imageRes
            )
        }
        OnbPageBottomStatic(
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.BottomCenter),
            onSkipClick = onOnboardingFinished,
            onNextClick = {
                if (pagerState.currentPage != onbPageDataList.size - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            pagerState = pagerState
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnboardingScreen() {
    OnboardingScreen(
        modifier = Modifier,
        onOnboardingFinished = {}
    )
}