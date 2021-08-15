package ru.sequence.film.data.datasource

import ru.sequence.film.data.api.FilmApi
import ru.sequence.film.data.dto.FilmDto

class FilmDataSourceImpl(private val api: FilmApi) : FilmDataSource {

	override suspend fun getAllFilms(): List<FilmDto> = api.getAllFilms().films
}