package ru.sequence.film.data.api

import retrofit2.http.GET
import ru.sequence.film.data.dto.FilmGroupDto

interface FilmApi {

	@GET("films.json")
	suspend fun getAllFilms(): FilmGroupDto
}