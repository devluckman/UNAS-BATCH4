package com.unas.filmku.model

data class MovieData (
    val image : String,
    val title : String,
    val rating : String
) {

    companion object {

        val dummy = listOf(
            MovieData(
                image = "",
                title = "Spiderman: No Way Home",
                rating = "9.1/10"
            ),
            MovieData(
                image = "",
                title = "Eternals",
                rating = "9.5/10"
            ),
            MovieData(
                image = "",
                title = "Venom Let There Be Carnage",
                rating = "8.1/10"
            ),
            MovieData(
                image = "",
                title = "The King’s Man",
                rating = "7.1/10"
            ),
            MovieData(
                image = "",
                title = "Shang-Chi",
                rating = "6.1/10"
            )
        )

    }

}