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
    @Query("SELECT * FROM habits")
    fun getAllHabits(): Flow<List<HabitDbEntity>>
    @Query("SELECT * FROM habits WHERE id = :id")
    fun getHabitById(id: Int): Flow<HabitDbEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitDbEntity)
    @Update
    suspend fun updateHabit(habit: HabitDbEntity)
    @Delete
    suspend fun deleteHabit(habit: HabitDbEntity)
}