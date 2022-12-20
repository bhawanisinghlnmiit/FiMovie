package com.example.fimovie.domain

import com.example.fimovie.model.dto.MovieResponse
import retrofit2.Response

interface MovieRepository {
    suspend fun getAllMovies(map: HashMap<String, Any>): Response<MovieResponse>
}
