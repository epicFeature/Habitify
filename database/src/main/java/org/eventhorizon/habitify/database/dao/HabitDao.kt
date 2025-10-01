package org.eventhorizon.habitify.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.eventhorizon.habitify.database.entity.HabitDbEntity


@Dao
interface HabitDao {

    // Получаем все привычки в виде Flow. При любом изменении в таблице habits,
    // этот Flow будет автоматически эмитить новый список.
    @Query("SELECT * FROM habits")
    fun getAllHabits(): Flow<List<HabitDbEntity>>

    // Получаем конкретную привычку по её id в виде Flow
    @Query("SELECT * FROM habits WHERE id = :id")
    fun getHabitById(id: Int): Flow<HabitDbEntity?>


    // Вставка новой привычки. OnConflictStrategy.REPLACE означает, что если
    // привычка с таким id уже есть, она будет заменена.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitDbEntity)

    // Обновление существующей привычки.
    @Update
    suspend fun updateHabit(habit: HabitDbEntity)

    // Удаление привычки.
    @Delete
    suspend fun deleteHabit(habit: HabitDbEntity)
}