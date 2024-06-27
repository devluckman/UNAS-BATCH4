package com.unas.filmku.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.BuildConfig
import com.unas.filmku.data.database.MovieDatabase
import com.unas.filmku.data.database.dao.MovieDao
import com.unas.filmku.data.remote.ApiService
import com.unas.filmku.data.repository.RepositoryImpl
import com.unas.filmku.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppDI {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun providesApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }


    @Provides
    fun providesRoomDatabase(
        @ApplicationContext context : Context
    ) : MovieDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MovieDatabase::class.java,
            "movie_unas"
        ).build()
    }

    @Provides
    fun providesMovieDao(
        movieDatabase: MovieDatabase
    ) : MovieDao  {
        return movieDatabase.movieDao()
    }


    @Provides
    fun providesRepository(
        apiService: ApiService,
        firebaseAuth: FirebaseAuth,
        movieDao: MovieDao
    ) : Repository {
        return RepositoryImpl(apiService, firebaseAuth, movieDao)
    }


}