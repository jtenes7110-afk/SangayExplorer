package com.example.sangayexplorer

import android.app.Application
import androidx.room.Room
import com.example.sangayexplorer.data.local.AppDatabase
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.sangayexplorer.data.local.DatabaseInitializer
import com.example.sangayexplorer.data.repository.RutaRepository

class SangayExplorerApp : Application() {

    val database: AppDatabase by lazy {

        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "sangay_database"
        ).build()

    }

    val repository by lazy {
        RutaRepository(database.rutaDao())
    }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInitializer.initialize(repository)
        }
    }

}