package org.eventhorizon.habitify.domain.usecase.database

import kotlinx.coroutines.flow.Flow
import org.eventhorizon.habitify.domain.model.database.Habit
import org.eventhorizon.habitify.domain.repository.database.HabitRepository
import javax.inject.Inject


/**
 * UseCase для получения одной привычки по ее идентификатору (ID) из репозитория.
 *
 * Этот класс инкапсулирует бизнес-логику (в данном случае, просто вызов репозитория)
 * и делает ViewModel независимой от конкретной реализации репозитория.
 */
class GetHabitByIdUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    /**
     * Перегрузка оператора invoke позволяет вызывать экземпляр класса как функцию.
     * Например: getHabitByIdUseCase(habitId)
     *
     * @param id Уникальный идентификатор привычки.
     * @return Flow, который эмитит объект Habit или null, если привычка не найдена.
     */
    operator fun invoke(id: Int): Flow<Habit?> {
        return habitRepository.getHabitById(id)
    }
}