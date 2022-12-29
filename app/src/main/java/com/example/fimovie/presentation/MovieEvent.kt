package com.example.fimovie.presentation

import com.example.fimovie.data.local.BookmarkMovie
import com.example.fimovie.model.dto.Search

sealed class MovieEvent {
    data class DeleteMovie(val movie : Search) : MovieEvent()
    object RestoreMovie: MovieEvent()
    data class InsertMovie(val movie : Search) : MovieEvent()
    object GetAllBookmarkMovies : MovieEvent()
//    data class GetMovie(val imdbId : String) : MovieEvent()
}