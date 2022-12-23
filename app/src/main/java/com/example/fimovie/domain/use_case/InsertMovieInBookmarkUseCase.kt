package com.example.fimovie.domain.use_case

import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.model.dto.Search
import javax.inject.Inject

class InsertMovieInBookmarkUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    // Check for movie type to pass here
    suspend operator fun invoke(movie : Search){
        repository.insertMovie(movie)
    }

}