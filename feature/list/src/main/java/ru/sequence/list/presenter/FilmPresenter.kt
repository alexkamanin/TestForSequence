package ru.sequence.list.presenter

import android.os.Bundle
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.entity.FilmGenre

interface FilmPresenter {

	fun onGenreSelected(isChecked: Boolean, genre: FilmGenre)
	fun onRepeatLoad()
	fun onItemClick(film: FilmEntity)
	fun onRestoredContent()

	fun getActiveGenre(): FilmGenre?
	fun onSaveState(outState: Bundle)
	fun onNewGenre(genre: FilmGenre)
}