package com.unas.filmku.domain.model

data class DetailMovieDomain(
    val id : Int,
    val image : String,
    val title : String,
    val rating : String,
    val duration : String,
    val description : String,
    val genres : List<String>,
    val isFavorite : Boolean = false
)