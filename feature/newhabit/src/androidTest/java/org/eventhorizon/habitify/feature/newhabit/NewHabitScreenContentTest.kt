package org.eventhorizon.habitify.feature.newhabit

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
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

    // Тестовые теги для надежного доступа к элементам
    private val habitNameTextFieldTag = "habitNameTextField"
    private val appearanceCardTitleTag = "appearanceCardTitle"

    // Дефолтное название для карточки Appearance.
    // В реальном проекте его лучше брать из строковых ресурсов.
    private val defaultAppearanceTitle = "Название привычки" // Предполагаемое дефолтное имя

    private val appearanceDays = MutableList(7) { it < 5 }.apply {shuffle(Random) }

    // Вспомогательная функция для создания валидного состояния
    private fun createInitialState(habitName: String): NewHabitContract.NewHabitUiState.State {
        return NewHabitContract.NewHabitUiState.State(
            habit = HabitUi(name = habitName),
            appearanceDays = appearanceDays
        )
    }

    @Test
    fun whenHabitNameIsEmpty_appearanceCardTitle_showsDefaultName() {
        // 1. Arrange: Подготовка состояния
        // Создаем начальное состояние с пустым именем привычки
        val initialState = createInitialState(habitName = "")

        // 2. Act: Отображение компонента
        composeTestRule.setContent {
            NewHabitScreenContent(
                state = initialState,
                onEvent = {} // В этом тесте обработка событий не нужна
            )
        }

        // ================== ШАГ ДЛЯ ОТЛАДКИ ==================
        // Выводим семантику всего экрана в Logcat с тегом "DebugEmptyName".
        // Это поможет увидеть, на каком элементе висит тег "appearanceCardTitle"
        // и какой у него текст (если есть).
        composeTestRule.onRoot().printToLog("DebugEmptyName")
        // ======================================================

        // 3. Assert: Проверка
        // Убеждаемся, что заголовок карточки Appearance показывает дефолтное значение
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(defaultAppearanceTitle))
    }

    @Test
    fun whenTypingInHabitName_appearanceCardTitle_updatesAccordingly() {
        // 1. Arrange: Подготовка
        // Используем mutableStateOf для симуляции обновлений состояния от ViewModel
        val state = mutableStateOf(createInitialState(""))
        val newHabitName = "Read a book for 15 minutes"

        // 2. Act: Отображение компонента и симуляция действий
        composeTestRule.setContent {
            NewHabitScreenContent(
                state = state.value,
                onEvent = { event ->
                    // Симулируем работу ViewModel:
                    // когда приходит событие изменения имени, мы обновляем наше тестовое состояние.
                    if (event is NewHabitContract.NewHabitUiEvent.OnHabitNameChanged) {
                        state.value = state.value.copy(
                            habit = state.value.habit.copy(name = event.name)
                        )
                    }
                }
            )
        }
        // ================== ШАГ ДЛЯ ОТЛАДКИ ==================
        // Выводим семантику всего экрана в Logcat с тегом "MyTestDebug".
        // Это нужно сделать ДО того, как тест упадет.
        composeTestRule.onRoot().printToLog("MyTestDebug")
        // ======================================================

        // Выполняем ввод текста в поле habitNameTextField
        composeTestRule.onNodeWithTag(habitNameTextFieldTag)
            .performTextInput(newHabitName)


        // ================== ЕЩЕ ОДИН ШАГ ДЛЯ ОТЛАДКИ ==================
        // Выводим семантику снова, уже ПОСЛЕ ввода текста, чтобы увидеть изменения.
        println("------ After text input ------")
        composeTestRule.onRoot().printToLog("MyTestDebug")
        // ============================================================

        // 3. Assert: Проверка
        // После ввода текста и обновления состояния, Compose должен был перерисовать UI.
        // Проверяем, что заголовок карточки Appearance теперь совпадает с введенным текстом.
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(newHabitName, ignoreCase = true))
    }

    @Test
    fun whenClearingHabitName_appearanceCardTitle_revertsToDefault() {
        // 1. Arrange: Подготовка
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

        // Assert (Initial state): Проверяем начальное состояние, ИГНОРИРУЯ РЕГИСТР
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(initialName, ignoreCase = true))

        // Act: Очищаем текстовое поле
        composeTestRule.onNodeWithTag(habitNameTextFieldTag)
            .performTextClearance()

        // Assert (Final state): Проверяем, что заголовок вернулся к дефолтному
        composeTestRule.onNodeWithTag(appearanceCardTitleTag)
            .assert(hasText(defaultAppearanceTitle))
    }
}
