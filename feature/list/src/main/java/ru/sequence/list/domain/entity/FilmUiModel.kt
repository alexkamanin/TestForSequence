package ru.sequence.list.domain.entity

import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.entity.FilmGenre

sealed class FilmUiModel {

	data class Cover(val filmEntity: FilmEntity) : FilmUiModel()

	data class Title(val title: Int) : FilmUiModel()

	data class Genre(val genre: Array<FilmGenre>) : FilmUiModel()
}