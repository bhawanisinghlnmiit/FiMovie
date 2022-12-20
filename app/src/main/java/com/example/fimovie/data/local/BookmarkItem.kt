package com.example.fimovie.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkItem(
    @PrimaryKey val imdbId : Int,
    @ColumnInfo(name = "poster")val Poster: String,
    @ColumnInfo(name = "title")val Title: String,
    @ColumnInfo(name = "type")val Type: String,
    @ColumnInfo(name = "year")val Year: String
)
