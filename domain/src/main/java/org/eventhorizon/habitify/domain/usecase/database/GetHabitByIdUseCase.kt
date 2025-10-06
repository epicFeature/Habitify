package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.flow.Flow
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject


class GetHabitByIdUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    operator fun invoke(id: Int): Flow<Habit?> {
        return habitRepository.getHabitById(id)
    }
}