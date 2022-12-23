package com.example.fimovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fimovie.model.dto.Search

@Database(entities = [Search::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}