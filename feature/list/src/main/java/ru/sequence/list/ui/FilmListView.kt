package ru.sequence.list.ui

import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.list.domain.entity.FilmUiModel

interface FilmListView {

	fun onDataReceived(cover: List<FilmUiModel>)
	fun onConnectionBroken()
	fun goToInfoFragment(film: FilmEntity)
	fun onStartedLoadData()
}
