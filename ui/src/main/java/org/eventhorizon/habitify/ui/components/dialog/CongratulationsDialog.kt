package org.eventhorizon.habitify.ui.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.eventhorizon.habitify.ui.R
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.AppColor.DarkPurple
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnDisablePurpleText
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnDisabledOrange
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnOrange
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.congratsDialogBtnTextStyle
import org.eventhorizon.habitify.ui.components.theme.congratsDialogSubtitleTextStyle
import org.eventhorizon.habitify.ui.components.theme.congratsDialogTitleTextStyle

@Composable
fun CongratulationsDialog(
    modifier: Modifier = Modifier,
    onContinue: () -> Unit,
    onCreateNewHabit: () -> Unit
) {
    Dialog(
        onDismissRequest = onContinue
    ) {
        Column(
            modifier
                .background(AppColor.White, Shapes.large)
                .fillMaxWidth()
                .wrapContentHeight(Alignment.Top)
                .semantics { contentDescription = "Congratulations Dialog" }
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            IconButton(
                onClick = onContinue,
                modifier = Modifier
                    .padding(12.dp)
                    .size(28.dp)
                    .align(Alignment.End)
                    .background(
                        color = AppColor.TopAppBarBackBtnBg,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = "Close dialog"
                )
            }
            Image(
                painter = painterResource(id = R.drawable.pic_congrat_dialog),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Поздравляем!".uppercase(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = congratsDialogTitleTextStyle
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Вы на шаг ближе к своей цели. Так держать!",
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Congratulations!" },
                textAlign = TextAlign.Center,
                style = congratsDialogSubtitleTextStyle
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = onCreateNewHabit,
                modifier = Modifier
                    .semantics { contentDescription = "Create new habit button" }
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 22.dp),
                shape = Shapes.small,
                colors = ButtonColors(
                    contentColor = DarkPurple,
                    containerColor = OnbBtnOrange,
                    disabledContentColor = OnbBtnDisablePurpleText,
                    disabledContainerColor = OnbBtnOrange
                )
            ) {
                Text(
                    text = "Создать новую привычку",
                    style = congratsDialogBtnTextStyle
                )
            }
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .semantics { contentDescription = "Continue button" }
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 22.dp),
                shape = Shapes.small,
                colors = ButtonColors(
                    contentColor = DarkPurple,
                    containerColor = OnbBtnDisabledOrange,
                    disabledContentColor = OnbBtnDisablePurpleText,
                    disabledContainerColor = OnbBtnDisabledOrange
                )
            ) {
                Text(
                    text = "Продолжить",
                    style = congratsDialogBtnTextStyle
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOnboardingScreen() {
    CongratulationsDialog(
        onContinue = {},
        onCreateNewHabit = {}
    )
}