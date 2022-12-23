package com.example.fimovie.domain.use_case

import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.model.dto.Search
import javax.inject.Inject

class DeleteBookmarkMovieUseCase @Inject constructor(
    private val repository : MovieRepository
) {
    suspend operator fun invoke(movie : Search) {
        repository.deleteMovie(movie)
    }
}