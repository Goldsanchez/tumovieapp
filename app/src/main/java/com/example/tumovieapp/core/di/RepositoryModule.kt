package com.example.tumovieapp.core.di

import com.example.tumovieapp.data.local.MovieDao
import com.example.tumovieapp.data.remote.MovieApiService
import com.example.tumovieapp.data.repository.MovieRepositoryImpl
import com.example.tumovieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApiService,
        dao: MovieDao
    ): MovieRepository {
        return MovieRepositoryImpl(api, dao)
    }
}