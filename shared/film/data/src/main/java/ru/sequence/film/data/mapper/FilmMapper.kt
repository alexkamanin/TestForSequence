package ru.sequence.film.data.mapper

import ru.sequence.film.data.dto.FilmDto
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.entity.FilmGenre

fun FilmDto.toEntity() = FilmEntity(id, localized_name, name, year, rating, image_url, description, genres.map { it.toGenre() })

fun String.toGenre(): FilmGenre = when (this) {
	"драма"       -> FilmGenre.DRAMA
	"фэнтези"     -> FilmGenre.FANTASY
	"криминал"    -> FilmGenre.CRIME
	"детектив"    -> FilmGenre.DETECTIVE
	"мелодрама"   -> FilmGenre.MELODRAMA
	"биография"   -> FilmGenre.BIOGRAPHY
	"комедия"     -> FilmGenre.COMEDY
	"фантастика"  -> FilmGenre.FANTASTIC
	"боевик"      -> FilmGenre.ACTION_MOVIE
	"триллер"     -> FilmGenre.THRILLER
	"мюзикл"      -> FilmGenre.MUSICAL
	"приключения" -> FilmGenre.ADVENTURES
	"ужасы"       -> FilmGenre.HORRORS
	else          -> throw UnsupportedOperationException("Unknown genre")
}