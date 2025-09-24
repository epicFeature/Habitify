package org.eventhorizon.habitify.ui.screens.newhabit.components.topcard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes
import org.eventhorizon.habitify.ui.theme.textFieldTextStyle

@Composable
fun HabitNameTextField(modifier: Modifier) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = Shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedTextColor = AppColor.DarkPurple, // Фиолетовый цвет вводимого текста
            unfocusedTextColor = AppColor.DarkPurple,
            focusedContainerColor = AppColor.White,
            unfocusedContainerColor = AppColor.White,
            focusedIndicatorColor = Color.Transparent, // Скрываем индикатор
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = AppColor.DarkPurple.copy(0.5F), // Цвет хинта
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
    HabitNameTextField(modifier = Modifier)
}
