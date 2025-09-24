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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.navigation.Routes
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.topAppBarTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    currentScreen: Routes,
    onBackClick: () -> Unit,
    habitName: String?
) {
    CenterAlignedTopAppBar(
        modifier = modifier.wrapContentHeight(),//todo оптимизировать и скорректировать работу
        title = {
            Text(
                text = if (currentScreen == Routes.valueOf(Routes.HABIT_INFO.name)) {
                    habitName ?: ""
                } else {
                    currentScreen.title?.let {
                        stringResource(
                            it
                        )
                    } ?: ""
                },
                modifier = Modifier.fillMaxWidth(0.85F),
                textAlign = TextAlign.Center,
                style = topAppBarTextStyle
                )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = AppColor.BgColorLightOrange
        ),
        navigationIcon = {
            when {
                currentScreen.hasBackButton -> {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp)
                            .background(
                                color = AppColor.TopAppBarBackBtnBg,
                                shape = CircleShape //todo проверить что сдесь лучше юзать
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
        currentScreen = Routes.NEW_HABIT,
        onBackClick = {},
        habitName = "Ранний подъём"
    )
}