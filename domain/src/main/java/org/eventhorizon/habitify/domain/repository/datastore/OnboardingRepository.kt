package org.eventhorizon.habitify.domain.repository.datastore

import kotlinx.coroutines.flow.Flow


/**
 * Репозиторий для управления состоянием онбординга.
 */
interface OnboardingRepository {

    /**
     * Предоставляет Flow, который эмитит true, если онбординг завершен, иначе false.
     * Значение по умолчанию (если ничего не сохранено) - false.
     */
    val isOnboardingFinished: Flow<Boolean>

    /**
     * Устанавливает, что онбординг завершен.
     */
    suspend fun finishOnboarding()
}