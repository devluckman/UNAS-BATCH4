package com.unas.filmku.data.mapper

import com.unas.filmku.data.database.entity.EntityMovie
import com.unas.filmku.data.response.DataDetailResponse
import com.unas.filmku.data.response.NowPlayingResponse
import com.unas.filmku.data.response.PopularResponse
import com.unas.filmku.domain.model.DetailMovieDomain
import com.unas.filmku.domain.model.MovieData

object Mapper {

    val baseUrlImage = "https://image.tmdb.org/t/p/w500"

    fun mappingMovieShowing(data: NowPlayingResponse): List<MovieData> {
        return data.results?.map {
            MovieData(
                image = baseUrlImage + it?.posterPath.orEmpty(),
                title = it?.title.orEmpty(),
                rating = it?.voteAverage.toString(),
                id = it?.id ?: 0
            )
        } ?: emptyList()
    }

    fun mappingMoviePopular(data: PopularResponse): List<MovieData> {
        return data.results?.map {
            MovieData(
                image = baseUrlImage + it?.posterPath.orEmpty(),
                title = it?.title.orEmpty(),
                rating = it?.voteAverage.toString(),
                id = it?.id ?: 0
            )
        } ?: emptyList()
    }

    fun mappingMovieDetail(data: DataDetailResponse): DetailMovieDomain {
        return DetailMovieDomain(
            id = data.id ?: 0,
            image = baseUrlImage + data.backdropPath.orEmpty(),
            title = data.title.orEmpty(),
            rating = data.voteAverage.toString(),
            duration = data.runtime.toString(),
            description = data.overview.orEmpty(),
            genres = data.genres?.map { it?.name.orEmpty() } ?: emptyList(),
        )
    }


    fun mappingEntityToDomain(data : List<EntityMovie>) : List<MovieData> {
        return data.map {
            MovieData(
                image = it.image,
                title = it.title,
                rating = it.rating,
                id = it.id
            )
        }
    }

    fun mappingMovieDomainToEntity(data : DetailMovieDomain) : EntityMovie {
        return EntityMovie(
            id = data.id,
            image = baseUrlImage + data.image,
            title = data.title,
            rating = data.rating,
            duration = data.duration,
            description = data.description,
            genres = data.genres.toString(),
        )
    }
}