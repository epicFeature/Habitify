package org.eventhorizon.habitify.domain.usecase

import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/**
 * Абстрактный базовый класс для всех тестов UseCase.
 * Он содержит общую настройку Mockito, избавляя от дублирования кода.
 * Все тестовые классы для UseCase должны наследоваться от него.
 */
abstract class BaseUseCaseTest {

    // Инициализирует все поля, аннотированные @Mock, @Spy, @Captor, @InjectMocks
    // в классах-наследниках. Это правило будет автоматически применяться ко всем тестам.
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    // В будущем сюда можно добавить общее правило для CoroutineDispatcher,
    // если потребуется более тонкое управление корутинами.
    // @get:Rule
    // val coroutineRule = MainCoroutineRule()
}