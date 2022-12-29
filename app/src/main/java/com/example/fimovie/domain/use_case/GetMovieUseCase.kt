package com.example.fimovie.domain.use_case

import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.model.dto.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(imdbId : String) : Flow<List<Search>> {
        return repository.getMovie(imdbId)
    }
}