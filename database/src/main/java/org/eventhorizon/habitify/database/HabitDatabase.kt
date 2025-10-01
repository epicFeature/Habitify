package org.eventhorizon.habitify.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.eventhorizon.habitify.database.converters.Converters
import org.eventhorizon.habitify.database.dao.HabitDao
import org.eventhorizon.habitify.database.entity.HabitDbEntity

@Database(
    entities = [HabitDbEntity::class],
    version = 1 // Начинаем с версии 1
)
@TypeConverters(Converters::class) // Регистрируем наши конвертеры
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {
        const val DATABASE_NAME = "habitify_db"

        // Пример миграции с версии 1 на 2. Пока пустой, но готов к использованию.
        // val MIGRATION_1_2 = object : Migration(1, 2) {
        //     override fun migrate(db: SupportSQLiteDatabase) {
        //         // Пример: db.execSQL("ALTER TABLE habits ADD COLUMN new_field TEXT")
        //     }
        // }
    }
}