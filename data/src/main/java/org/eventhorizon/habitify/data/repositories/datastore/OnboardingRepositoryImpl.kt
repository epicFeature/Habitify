package org.eventhorizon.habitify.data.repositories.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.eventhorizon.habitify.domain.repository.datastore.OnboardingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {

    private object PreferencesKeys {
        val IS_ONBOARDING_FINISHED_KEY = booleanPreferencesKey("is_onboarding_finished")
    }

    override val isOnboardingFinished: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.IS_ONBOARDING_FINISHED_KEY] ?: false
        }

    override suspend fun finishOnboarding() {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_ONBOARDING_FINISHED_KEY] = true
        }
    }
}