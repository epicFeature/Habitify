package org.eventhorizon.habitify.feature.newhabit.components.topcard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.textFieldTextStyle

@Composable
fun HabitNameTextField(
    modifier: Modifier,
    habitName: String,
    onHabitNameChanged: (String)->Unit
) {

    TextField(
        value = habitName,
        onValueChange = onHabitNameChanged,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = Shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedTextColor = AppColor.DarkPurple,
            unfocusedTextColor = AppColor.DarkPurple,
            focusedContainerColor = AppColor.White,
            unfocusedContainerColor = AppColor.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = AppColor.DarkPurple.copy(0.5F),
            unfocusedPlaceholderColor = AppColor.DarkPurple.copy(0.5F)
        ),
        placeholder = {
            Text(text = "Enter habit name", style = textFieldTextStyle) // Ваш хинт
        },
        textStyle = textFieldTextStyle
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitNameTextField() {
    HabitNameTextField(
        modifier = Modifier,
        habitName = "",
        onHabitNameChanged = {})
}
