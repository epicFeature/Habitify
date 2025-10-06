package org.eventhorizon.habitify.domain.repository.datastore

import kotlinx.coroutines.flow.Flow


interface OnboardingRepository {
    val isOnboardingFinished: Flow<Boolean>
    suspend fun finishOnboarding()
}