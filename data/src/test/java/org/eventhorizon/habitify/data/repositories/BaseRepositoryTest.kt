package org.eventhorizon.habitify.data.repositories

import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class BaseRepositoryTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()
}