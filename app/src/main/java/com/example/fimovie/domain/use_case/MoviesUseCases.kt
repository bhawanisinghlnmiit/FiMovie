package com.example.fimovie.domain.use_case

data class MoviesUseCases(
    val deleteBookmarkMovieUseCase: DeleteBookmarkMovieUseCase,
    val getBookmarkMoviesUseCase: GetBookmarkMoviesUseCase,
    val insertMovieInBookmarkUseCase: InsertMovieInBookmarkUseCase,
    val getMovieByImdb : GetMovieUseCase
)