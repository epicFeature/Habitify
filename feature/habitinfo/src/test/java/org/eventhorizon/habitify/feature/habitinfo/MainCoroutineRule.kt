package org.eventhorizon.habitify.feature.habitinfo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi    import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description


/**
 * JUnit Rule, который заменяет главный диспетчер (Dispatchers.Main) на тестовый
 * на время выполнения теста. Это позволяет корутинам, использующим viewModelScope,
 * выполняться синхронно в тестах.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(
    // Позволяет использовать разные диспатчеры, UnconfinedTestDispatcher подходит для большинства ViewModel тестов
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher) // Заменяем Main на тестовый
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain() // Возвращаем реальный Main диспетчер
    }
}