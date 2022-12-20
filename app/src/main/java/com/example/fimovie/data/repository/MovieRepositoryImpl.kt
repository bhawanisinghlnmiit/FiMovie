package com.example.fimovie.data.repository

import com.example.fimovie.data.remote.MovieApiService
import com.example.fimovie.model.dto.MovieResponse
import com.example.fimovie.domain.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApiService: MovieApiService) :
    MovieRepository {
    override suspend fun getAllMovies(map: HashMap<String, Any>): Response<MovieResponse> {
        return movieApiService.getAllMovies(map)
    }
}
