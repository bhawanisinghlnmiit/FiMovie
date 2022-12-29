package com.example.fimovie.data.repository

import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.data.local.MovieDao
import com.example.fimovie.data.local.MovieDatabase
import com.example.fimovie.data.remote.MovieApiService
import com.example.fimovie.model.dto.MovieResponse
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.model.dto.Search
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val dao : MovieDao
    ) :
    MovieRepository {

    override suspend fun getAllMovies(map: HashMap<String, Any>): Response<MovieResponse> {
        return movieApiService.getAllMovies(map)
    }

    override fun getBookmarkMovies(): Flow<List<Search>> {
       return dao.getBookmarkMovies()
    }

    override suspend fun insertMovie(movie: Search) {
        dao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Search) {
        dao.deleteMovie(movie)
    }

    override fun getMovie(imdbId: String): Flow<List<Search>> {
        return dao.getMovie(imdbId)
    }

}
