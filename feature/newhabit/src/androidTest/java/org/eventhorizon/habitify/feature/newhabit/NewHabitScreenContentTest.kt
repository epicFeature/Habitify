package org.eventhorizon.habitify.feature.newhabit

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.eventhorizon.habitify.feature.newhabit.model.HabitUi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class NewHabitScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val habitNameTextFieldTag = "habitNameTextField"
    private val appearanceCardTitleTag = "appearanceCardTitle"
    private val defaultAppearanceTitle = "Название привычки"
    private val appearanceDays = MutableList(7) { it < 5 }.apply {shuffle(Random) }
    private fun createInitialState(habitName: String): NewHabitContract.NewHabitUiState.State {
        return NewHabitContract.NewHabitUiState.State(
            habit = HabitUi(name = habitName),
            appearanceDays = appearanceDays
        )
    }

    @Test
    fun whenHabitNameIsEmpty_appearanceCardTitle_showsDefaultName() {
        // 1. Arrange
        val initialState = createInitialState(habitName = "")
        // 2. Act
        composeTestRule.setContent {
            NewHabitScreenContent(
                state = initialState,
                onEvent = {}
            )
        }
        // 3. Assert
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(defaultAppearanceTitle))
    }

    @Test
    fun whenTypingInHabitName_appearanceCardTitle_updatesAccordingly() {
        // 1. Arrange
        val state = mutableStateOf(createInitialState(""))
        val newHabitName = "Read a book for 15 minutes"
        // 2. Act
        composeTestRule.setContent {
            NewHabitScreenContent(
                state = state.value,
                onEvent = { event ->
                    if (event is NewHabitContract.NewHabitUiEvent.OnHabitNameChanged) {
                        state.value = state.value.copy(
                            habit = state.value.habit.copy(name = event.name)
                        )
                    }
                }
            )
        }
        composeTestRule.onNodeWithTag(habitNameTextFieldTag)
            .performTextInput(newHabitName)
        // 3. Assert
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(newHabitName, ignoreCase = true))
    }

    @Test
    fun whenClearingHabitName_appearanceCardTitle_revertsToDefault() {
        // 1. Arrange
        val initialName = "Go for a walk"
        val state = mutableStateOf(createInitialState(habitName = initialName))
        composeTestRule.setContent {
            NewHabitScreenContent(
                state = state.value,
                onEvent = { event ->
                    if (event is NewHabitContract.NewHabitUiEvent.OnHabitNameChanged) {
                        state.value = state.value.copy(
                            habit = state.value.habit.copy(name = event.name)
                        )
                    }
                }
            )
        }
        // Assert (начальн состояние)
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(initialName, ignoreCase = true))
        // Act
        composeTestRule.onNodeWithTag(habitNameTextFieldTag)
            .performTextClearance()
        // Assert (итоговое состояние)
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(defaultAppearanceTitle))
    }
}
