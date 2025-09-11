

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.theme.onbTitleTextStyle


@Composable
fun OnbPageBasicTopDynamic(
    modifier: Modifier = Modifier,
    titleRes: Int,
    imageRes: Int) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(36.dp))
        Text(
            text = stringResource(titleRes),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            textAlign = TextAlign.Center,
            style = onbTitleTextStyle
        )
        Spacer(Modifier.height(36.dp))
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .wrapContentHeight()
        )
        Spacer(Modifier.height(120.dp))
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewOnbPageBasicTopDynamic(){
    OnbPageBasicTopDynamic(
        modifier = Modifier,
        titleRes = R.string.onb_screen_title_3,
        imageRes = R.drawable.bg_onb_3
    )
}
