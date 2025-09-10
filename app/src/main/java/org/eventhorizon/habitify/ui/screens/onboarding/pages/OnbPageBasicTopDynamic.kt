

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.R


@Composable
fun OnbPageBasicTopDynamic(modifier: Modifier = Modifier, title: String, subtitle: String) {
    Column(
        modifier = modifier
    ) {
        Spacer(Modifier.height(80.dp))
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            textAlign = TextAlign.Center,
            //style = serverItemTitleTextStyle
        )
        Spacer(Modifier.height(28.dp))
        Text(
            text = subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            textAlign = TextAlign.Center,
            //style = serverItemSubtitleTextStyle
        )
        Spacer(Modifier.height(450.dp))
        Image(
            painter = painterResource(id = R.drawable.bg_onb_2),
            contentDescription = null,
            modifier = Modifier
                .padding(32.dp)
                .height(300.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewOnbPageBasicTopDynamic(){
    OnbPageBasicTopDynamic(
        modifier = Modifier,
        title = stringResource(R.string.onb_screen_title_1_2),
        subtitle = stringResource(R.string.onb_screen_subtitle)
    )
}
