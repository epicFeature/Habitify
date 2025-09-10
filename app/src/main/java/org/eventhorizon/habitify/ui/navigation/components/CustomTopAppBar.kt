package android.ruvpn.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.ui.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    currentScreen: Routes,
    onBackClick: () -> Unit,
    habitName: String?
) {
    CenterAlignedTopAppBar( //todo оптимизировать и скорректировать работу
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
                //style = serverItemTitleTextStyle,
                //color = AppColor.TextSecondaryBlackTransparent60
                )
        },
        /*colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = AppColor.White
        ),*/
        navigationIcon = {
            /*when {
                currentScreen.hasBackButton -> {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(R.drawable.ic_back_btn),
                            contentDescription = "Back"
                        )
                    }
                }

                else -> {}
            }*/
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCustomTopAppBar() {
    CustomTopAppBar(
        currentScreen = Routes.HABIT_INFO,
        onBackClick = {},
        habitName = "Ранний подъём"
    )
}