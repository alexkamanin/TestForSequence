package ru.sequence.film.data.dto

data class FilmGroupDto(
	val films: List<FilmDto>
)

data class FilmDto(
	val id: Long,
	val localized_name: String,
	val name: String,
	val year: Int,
	val rating: Float,
	val image_url: String?,
	val description: String?,
	val genres: List<String>
)