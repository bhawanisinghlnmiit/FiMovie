package com.example.fimovie.model.dto

data class MovieResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
