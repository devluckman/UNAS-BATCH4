package com.unas.filmku.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.unas.filmku.data.database.entity.EntityMovie


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovieBookmark() : List<EntityMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToBookmark(data : EntityMovie)

}