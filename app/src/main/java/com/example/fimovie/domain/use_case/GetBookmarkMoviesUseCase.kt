package com.example.fimovie.domain.use_case

import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.model.dto.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkMoviesUseCase @Inject constructor(
    private val repository : MovieRepository
) {
    operator fun invoke() : Flow<List<Search>>{
        return repository.getBookmarkMovies()
    }
}