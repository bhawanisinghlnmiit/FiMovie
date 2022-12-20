package com.example.fimovie.data.remote

import com.example.fimovie.model.dto.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApiService {
    @GET("/")
    suspend fun getAllMovies(@QueryMap map: HashMap<String, Any>): Response<MovieResponse>
}