package com.example.fimovie.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fimovie.data.local.MovieDao
import com.example.fimovie.data.local.MovieDatabase
import com.example.fimovie.data.remote.MovieApiService
import com.example.fimovie.data.repository.MovieRepositoryImpl
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.domain.use_case.DeleteBookmarkMovieUseCase
import com.example.fimovie.domain.use_case.GetBookmarkMoviesUseCase
import com.example.fimovie.domain.use_case.InsertMovieInBookmarkUseCase
import com.example.fimovie.domain.use_case.MoviesUseCases
import com.example.fimovie.utils.Constants
import com.example.fimovie.utils.Constants.MOVIE_DATABASE
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInterface(): MovieApiService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) : MovieDatabase{
        return Room
            .databaseBuilder(
                context,
                MovieDatabase::class.java,
                MOVIE_DATABASE)
            .build()
    }


    @Singleton
    @Provides
    fun provideMovieUseCases(repository : MovieRepository) : MoviesUseCases{
        return MoviesUseCases(
            DeleteBookmarkMovieUseCase(repository),
            GetBookmarkMoviesUseCase(repository),
            InsertMovieInBookmarkUseCase(repository)
        )
    }
}

@InstallIn(SingletonComponent::class)
@Module
object Module{
    @Provides
    @Singleton
    fun provideMovieRepository(movieApiService: MovieApiService, db : MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(movieApiService, db.movieDao())
    }

}



