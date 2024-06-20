package com.unas.filmku.di

import com.google.firebase.auth.FirebaseAuth
import com.unas.filmku.BuildConfig
import com.unas.filmku.data.remote.ApiService
import com.unas.filmku.data.repository.RepositoryImpl
import com.unas.filmku.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providesRepository(
        apiService: ApiService,
        firebaseAuth: FirebaseAuth
    ) : Repository {
        return RepositoryImpl(apiService, firebaseAuth)
    }


}