package com.unas.filmku.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unas.filmku.data.database.dao.MovieDao
import com.unas.filmku.data.database.entity.EntityMovie

@Database(entities = [EntityMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

}