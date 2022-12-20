package com.example.fimovie.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fimovie.model.FilterState
import com.example.fimovie.model.dto.Search
import com.example.fimovie.utils.Constants
import java.util.logging.Filter
import javax.inject.Inject

class MoviePaging @Inject constructor(private val s: String,private val t : FilterState, private val movieRepository: MovieRepository) :
    PagingSource<Int, Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        val page = params.key ?: 1
        return try {
            val data = movieRepository.getAllMovies(
                hashMapOf(
                    "s" to s,
                    "page" to page,
                    "apikey" to Constants.API_KEY,
                    "type" to when(t){
                        FilterState.SERIES -> {
                            "series"
                        }
                        FilterState.MOVIES -> {
                            "movie"
                        }
                        FilterState.EPISODES -> {
                            "episode"
                        }
                        else -> {
                            ""
                        }
                    }
                )
            )

            LoadResult.Page(
                data = data.body()?.Search!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.Search?.isEmpty()!!) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
