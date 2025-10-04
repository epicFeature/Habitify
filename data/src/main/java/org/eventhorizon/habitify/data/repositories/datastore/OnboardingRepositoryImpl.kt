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

    // Создаем ключ, по которому будет храниться наше значение.
    private object PreferencesKeys {
        val IS_ONBOARDING_FINISHED = booleanPreferencesKey("is_onboarding_finished")
    }

    override val isOnboardingFinished: Flow<Boolean> = dataStore.data
        .map { preferences ->
            // Читаем значение по ключу. Если его там нет, возвращаем 'false' по умолчанию.
            preferences[PreferencesKeys.IS_ONBOARDING_FINISHED] ?: false
        }

    override suspend fun finishOnboarding() {
        // Безопасно изменяем значение в DataStore.
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_ONBOARDING_FINISHED] = true
        }
    }
}