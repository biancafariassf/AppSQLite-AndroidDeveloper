package com.example.appdisney.data

import android.content.Context
import androidx.room.Room

class AppContainer(context: Context) {
    private val database: DisneyDatabase by lazy {
        Room.databaseBuilder(context, DisneyDatabase::class.java, "db_disney").build()
    }

    val disneyRepository: DisneyRepository by lazy {
        DisneyRepository(database.disneyDao())
    }
}