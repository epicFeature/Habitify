
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.screens.onboarding.components.OnbPagerIndicator
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes

@Composable
fun OnbPageBottomStatic(
    modifier: Modifier,
    onClick: () -> Unit,
    pagerState: PagerState
) {
    Column(
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.onb_screen_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            textAlign = TextAlign.Center,
            //style = serverItemTitleTextStyle
        )
        Spacer(Modifier.height(54.dp))
        Row(Modifier.wrapContentHeight()) {

        }
        Spacer(Modifier.height(36.dp))
        OnbPagerIndicator(pagerState)
        Spacer(Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnbPageBottomStatic() {
    OnbPageBottomStatic(
        modifier = Modifier,
        onClick = {},
        pagerState = PagerState { 3 })
}