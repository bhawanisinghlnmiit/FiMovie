package com.example.fimovie.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.edit
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.fimovie.data.local.MovieDao
import com.example.fimovie.data.local.MovieDatabase
import com.example.fimovie.data.remote.MovieApiService
import com.example.fimovie.data.repository.MovieRepositoryImpl
import com.example.fimovie.domain.MovieRepository
import com.example.fimovie.domain.use_case.*
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInterface(
        client: OkHttpClient
    ): MovieApiService {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun gmHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.writeTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)
      //  builder.addNetworkInterceptor(StethoInterceptor())
        //builder.addInterceptor(OkHttpProfilerInterceptor())
        context?.let { context ->
            builder.addInterceptor(ChuckerInterceptor(context))
        }
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }).retryOnConnectionFailure(true)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room
            .databaseBuilder(
                context,
                MovieDatabase::class.java,
                MOVIE_DATABASE
            )
            .build()
    }


    @Singleton
    @Provides
    fun provideMovieUseCases(repository: MovieRepository): MoviesUseCases {
        return MoviesUseCases(
            DeleteBookmarkMovieUseCase(repository),
            GetBookmarkMoviesUseCase(repository),
            InsertMovieInBookmarkUseCase(repository),
            GetMovieUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        db: MovieDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(movieApiService, db.movieDao())
    }
}




