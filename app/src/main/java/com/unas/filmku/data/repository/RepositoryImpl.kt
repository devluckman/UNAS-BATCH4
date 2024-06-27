package com.unas.filmku.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unas.filmku.BuildConfig
import com.unas.filmku.data.database.dao.MovieDao
import com.unas.filmku.data.mapper.Mapper
import com.unas.filmku.data.remote.ApiService
import com.unas.filmku.domain.model.DetailMovieDomain
import com.unas.filmku.domain.model.MovieData
import com.unas.filmku.domain.model.UserData
import com.unas.filmku.domain.repository.Repository
import com.unas.filmku.domain.request.RequestRegister
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val api: ApiService, // DATA API
    private val firebaseAuth: FirebaseAuth, // DATA Firebase
    private val database: MovieDao // DATABASE
) : Repository {

    override val isLogin: Boolean
        get() = firebaseAuth.currentUser != null

    override fun postLogin(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Action Login Success
                callback.invoke(true)
            }.addOnFailureListener { data ->
                data.printStackTrace()
                // Action Login Failed
                callback.invoke(false)
            }
    }

    override fun postRegister(requestRegister: RequestRegister, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(requestRegister.email, requestRegister.password)
            .addOnSuccessListener { auth ->
                // Action after register success
                // Save data user to Firestore
                val uid = auth.user?.uid ?: "uid"
                val data = UserData(
                    email = requestRegister.email,
                    name = "${requestRegister.firstName} ${requestRegister.lastName}"
                )
                // Implementation Write Data to FireStore
                Firebase.firestore.collection("user")
                    .document(uid)
                    .set(data)
                    .addOnSuccessListener {

                        // Action Register and Save Data Success
                        callback.invoke(true)
                    }

            }.addOnFailureListener { data ->
                data.printStackTrace()
                callback.invoke(false)
            }
    }

    override fun getMovieShowing(): Flow<List<MovieData>> = flow {
        try {
            val token = BuildConfig.TOKEN

            val data = api.getNowPlayingMovie(token = "Bearer $token")

            emit(Mapper.mappingMovieShowing(data))

        } catch (e: Exception) {

            e.printStackTrace()
            emit(emptyList())

        }
    }

    override fun getMoviePopular(): Flow<List<MovieData>> = flow {
        try {
            val token = BuildConfig.TOKEN

            val data = api.getPopularMovie(token = "Bearer $token")

            emit(Mapper.mappingMoviePopular(data))
        } catch (e: Exception) {
            e.printStackTrace()

            emit(emptyList())
        }
    }

    override fun getMovieDetail(id: Int): Flow<DetailMovieDomain> = flow {
        try {
            val token = BuildConfig.TOKEN

            val data = api.getDetailMovie(token = "Bearer $token", movieId = id)

            emit(Mapper.mappingMovieDetail(data))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getAllMovieBookmark(): Flow<List<MovieData>> = flow {

        val data = database.getAllMovieBookmark()

        emit(Mapper.mappingEntityToDomain(data))
    }

    override fun addMovieToDatabase(data: DetailMovieDomain): Flow<Boolean> = flow {
        try {
            val entity = Mapper.mappingMovieDomainToEntity(data)
            database.addMovieToBookmark(entity) // Fungsi Insert

            emit(true)
        } catch (e: Exception) {
            emit(false)
            e.printStackTrace()
        }

    }

}