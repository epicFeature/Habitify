package org.eventhorizon.habitify.ui.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.theme.AppColor

@Composable
fun OnbPagerIndicator(modifier: Modifier, pagerState: PagerState, size: Dp = 12.dp) {
    val orangeCircleSize = size
    val purpleCircleSize = size * 1.2f
    val glowSize = purpleCircleSize * 1.4f

    Row(
        modifier
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->
            if (pagerState.currentPage == iteration) {
                Box(
                    modifier = Modifier.padding(4.dp).size(glowSize),
                    contentAlignment = Center
                ) {
                    // Ореол
                    Box(
                        modifier = Modifier
                            .size(glowSize)
                            .background(AppColor.OnbDotPurpleShadow, CircleShape)
                    )
                    // Основной круг
                    Box(
                        modifier = Modifier
                            .size(purpleCircleSize)
                            .background(AppColor.OnbDotPurple, CircleShape)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(AppColor.OnbDotOrange)
                        .size(orangeCircleSize)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOnbPagerIndicator() {
    OnbPagerIndicator(modifier = Modifier, pagerState = PagerState { 4 })
}