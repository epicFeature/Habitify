package android.ruvpn.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.R
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.topAppBarTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackBtn: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier.wrapContentHeight(),
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(0.85F),
                textAlign = TextAlign.Center,
                style = topAppBarTextStyle
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppColor.BgColorLightOrange,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        ),
        navigationIcon = {
            when {
                showBackBtn -> {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp)
                            .background(
                                color = AppColor.TopAppBarBackBtnBg,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back_btn),
                            contentDescription = "Back"
                        )
                    }
                }
                else -> {}
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCustomTopAppBar() {
    CustomTopAppBar(
        title = "Ранний подъём"
    )
}