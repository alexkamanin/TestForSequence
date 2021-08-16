package ru.sequence.film.data.datasource

import ru.sequence.film.data.dto.FilmDto

interface FilmDataSource {

	suspend fun getAllFilms(): List<FilmDto>
}