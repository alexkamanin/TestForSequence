package ru.sequence.film.domain.entity

import java.io.Serializable

data class FilmEntity(
	val id: Long,
	val localized_name: String,
	val name: String,
	val year: Int,
	val rating: Float,
	val image_url: String?,
	val description: String?,
	val genres: List<FilmGenre>
) : Serializable