package org.eventhorizon.habitify.domain.usecase.datastore

import org.eventhorizon.habitify.domain.repository.datastore.OnboardingRepository
import javax.inject.Inject

class FinishOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke() {
        onboardingRepository.finishOnboarding()
    }
}