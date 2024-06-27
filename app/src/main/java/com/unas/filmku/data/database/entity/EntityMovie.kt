package com.unas.filmku.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class EntityMovie(
    @PrimaryKey
    val id : Int,
    val image : String,
    val title : String,
    val rating : String,
    val duration : String,
    val description : String,
    val genres : String,
    val isFavorite : Boolean = false
)

// data = [data, data, data]
// genres = "data,data,data"
//