package com.example.sangayexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sangayexplorer.data.local.dao.RutaDao
import com.example.sangayexplorer.data.model.Ruta
import android.content.Context
import androidx.room.Room

@Database(
    entities = [Ruta::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rutaDao(): RutaDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sangay_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }

}

