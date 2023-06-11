package cz.mendelu.pef.xchyliko.coffeeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee

@Database(entities = [Coffee::class], version = 8, exportSchema = true)
abstract class CoffeeDatabase : RoomDatabase() {

    abstract fun coffeeDao(): CoffeeDao

    companion object {
        private var INSTANCE: CoffeeDatabase? = null
        fun getDatabase(context: Context): CoffeeDatabase {
            if (INSTANCE == null) {
                synchronized(CoffeeDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CoffeeDatabase::class.java, "tasks_database"
                        ).fallbackToDestructiveMigration() // nesmi se dostat do produkce
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}