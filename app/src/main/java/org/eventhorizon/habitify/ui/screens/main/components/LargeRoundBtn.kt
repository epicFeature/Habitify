package org.eventhorizon.habitify.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.theme.AppColor

@Composable
fun LargeRoundBtn(
    modifier: Modifier = Modifier,
    iconData: IconData = IconData(R.drawable.ic_add, "Add"),
    onClick: () -> Unit,
    size: Int = 64,
    iconSizeCoef: Double = 0.5
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(AppColor.circleBtnColor.copy(0.2F))
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.Center)
                .size((size * 0.8).dp)
                .background(
                    color = AppColor.circleBtnColor,
                    shape = CircleShape //todo проверить что сдесь лучше юзать
                )
        ) {
            Icon(
                painter = painterResource(iconData.iconRes),
                contentDescription = iconData.contentDescription,
                modifier = Modifier.size((size*iconSizeCoef).dp),
                tint = AppColor.DarkPurple
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLargeRoundBtn() {
    LargeRoundBtn(onClick = {})
}

data class IconData(
    val iconRes: Int,
    val contentDescription: String
)