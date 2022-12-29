package com.example.fimovie.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fimovie.model.dto.Search
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM search")
    fun getBookmarkMovies() : Flow<List<Search>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(bookmarkMovie: Search)

    @Delete
    suspend fun deleteMovie(bookmarkMovie: Search)

    @Query("Select * FROM search where imdbId = :imdbId")
    fun getMovie(imdbId : String) : Flow<List<Search>>

}