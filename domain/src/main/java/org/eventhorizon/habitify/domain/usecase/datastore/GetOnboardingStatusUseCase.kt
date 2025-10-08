package org.eventhorizon.habitify.domain.usecase.datastore

import kotlinx.coroutines.flow.Flow
import org.eventhorizon.habitify.domain.repository.datastore.OnboardingRepository
import javax.inject.Inject

class GetOnboardingStatusUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return onboardingRepository.isOnboardingFinished
    }
}