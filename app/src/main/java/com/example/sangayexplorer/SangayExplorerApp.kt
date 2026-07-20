package com.example.sangayexplorer

import android.app.Application
import androidx.room.Room
import com.example.sangayexplorer.data.local.AppDatabase

class SangayExplorerApp : Application() {

    val database: AppDatabase by lazy {

        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "sangay_database"
        ).build()

    }

}