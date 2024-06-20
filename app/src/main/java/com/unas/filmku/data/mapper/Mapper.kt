package com.unas.filmku.data.mapper

import com.unas.filmku.data.response.NowPlayingResponse
import com.unas.filmku.data.response.PopularResponse
import com.unas.filmku.domain.model.MovieData

object Mapper {

    val baseUrlImage = "https://image.tmdb.org/t/p/w500"

    fun mappingMovieShowing(data : NowPlayingResponse) : List<MovieData> {
        return data.results?.map {
            MovieData(
                image = baseUrlImage + it?.posterPath.orEmpty(),
                title = it?.title.orEmpty(),
                rating = it?.voteAverage.toString()
            )
        } ?: emptyList()
    }

    fun mappingMoviePopular(data : PopularResponse) : List<MovieData> {
        return data.results?.map {
            MovieData(
                image = baseUrlImage + it?.posterPath.orEmpty(),
                title = it?.title.orEmpty(),
                rating = it?.voteAverage.toString()
            )
        } ?: emptyList()
    }

}