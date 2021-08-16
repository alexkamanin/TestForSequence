package ru.sequence.film.domain.entity

import ru.sequence.film.domain.R
import java.io.Serializable

enum class FilmGenre(val res: Int) : Serializable {
	DRAMA(R.string.film_genre_drama),
	FANTASY(R.string.film_genre_fantasy),
	CRIME(R.string.film_genre_crime),
	DETECTIVE(R.string.film_genre_detective),
	MELODRAMA(R.string.film_genre_melodrama),
	BIOGRAPHY(R.string.film_genre_biography),
	COMEDY(R.string.film_genre_comedy),
	FANTASTIC(R.string.film_genre_fantastic),
	ACTION_MOVIE(R.string.film_genre_action_movie),
	THRILLER(R.string.film_genre_thriller),
	MUSICAL(R.string.film_genre_musical),
	ADVENTURES(R.string.film_genre_adventures),
	HORRORS(R.string.film_genre_horrors)
}