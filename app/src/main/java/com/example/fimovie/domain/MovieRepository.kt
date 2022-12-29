package com.example.fimovie.domain

import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.model.dto.MovieResponse
import com.example.fimovie.model.dto.Search
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {
    suspend fun getAllMovies(map: HashMap<String, Any>): Response<MovieResponse>

    fun getBookmarkMovies() : Flow<List<Search>>
    suspend fun insertMovie(movie: Search)
    suspend fun deleteMovie(movie: Search)
    fun getMovie(imdbId : String) : Flow<List<Search>>

}
