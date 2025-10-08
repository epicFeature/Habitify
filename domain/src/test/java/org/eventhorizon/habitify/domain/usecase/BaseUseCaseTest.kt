package org.eventhorizon.habitify.domain.usecase

import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class BaseUseCaseTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
}