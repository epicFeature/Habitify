package org.eventhorizon.habitify.ui.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.R
import org.eventhorizon.habitify.ui.components.theme.AppColor

@Composable
fun LargeRoundBtn(
    modifier: Modifier = Modifier,
    iconData: IconData = IconData(R.drawable.ic_add, "Add"),
    onClick: () -> Unit,
    size: Int = 64,
    iconSizeCoefficient: Double = 0.5,
    enabled: Boolean = true
) {
    val buttonColor = if (enabled) AppColor.circleBtnColor else AppColor.circleBtnColor.copy(alpha = 0.12f)
    val iconColor = if (enabled) AppColor.DarkPurple else AppColor.DarkPurple.copy(alpha = 0.38f)
    val glowColor = if (enabled) AppColor.circleBtnColor.copy(0.2F) else Color.Transparent

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(glowColor)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.Center)
                .size((size * 0.8).dp)
                .background(
                    color = buttonColor,
                    shape = CircleShape
                ),
            enabled = enabled
        ) {
            Icon(
                painter = painterResource(iconData.iconRes),
                contentDescription = iconData.contentDescription,
                modifier = Modifier.size((size*iconSizeCoefficient).dp),
                tint = iconColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLargeRoundBtn() {
    LargeRoundBtn(
        onClick = {},
        enabled = false
    )
}

data class IconData(
    val iconRes: Int,
    val contentDescription: String
)