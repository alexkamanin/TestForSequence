package ru.sequence.film.domain.repository

import ru.sequence.film.domain.entity.FilmEntity

interface FilmRepository {

	suspend fun getAllFilms(): List<FilmEntity>
}