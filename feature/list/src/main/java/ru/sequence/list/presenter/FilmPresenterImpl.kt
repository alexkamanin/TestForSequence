package ru.sequence.list.presenter

import android.os.Bundle
import kotlinx.coroutines.*
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.entity.FilmGenre
import ru.sequence.film.domain.usecase.GetAllFilmsUseCase
import ru.sequence.list.R
import ru.sequence.list.domain.entity.FilmUiModel
import ru.sequence.list.ui.FilmListView
import kotlin.coroutines.CoroutineContext

class FilmPresenterImpl(
	private val view: FilmListView,
	private val getAllFilmsUseCase: GetAllFilmsUseCase
) : FilmPresenter, CoroutineScope {

	override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

	private var films: MutableList<FilmUiModel> = mutableListOf()
	private var activeGenre: FilmGenre? = null
	private var job: Job? = null

	init {
		films.add(FilmUiModel.Title(R.string.film_genres_title))
		films.add(FilmUiModel.Genre(FilmGenre.values()))
		films.add(FilmUiModel.Title(R.string.film_title))
		rescheduleJob()
	}

	private fun rescheduleJob() {
		job?.cancel()
		job = launch {
			try {
				withContext(Dispatchers.Main) { view.onStartedLoadData() }
				films.addAll(getAllFilmsUseCase().map { FilmUiModel.Cover(it) }.sortedBy { it.filmEntity.localized_name })
				if (activeGenre != null)
					withContext(Dispatchers.Main) { view.onDataReceived(getSortedFilmsByGenre(activeGenre!!)) }
				else
					withContext(Dispatchers.Main) { view.onDataReceived(films) }
			} catch (e: Exception) {
				withContext(Dispatchers.Main) { view.onConnectionBroken() }

			}
		}
	}

	private fun getSortedFilmsByGenre(genre: FilmGenre): List<FilmUiModel> = films.filter {

		if (it is FilmUiModel.Cover) {
			it.filmEntity.genres.contains(genre)
		} else {
			true
		}
	}

	override fun onGenreSelected(isChecked: Boolean, genre: FilmGenre) {

		if (isChecked) {
			activeGenre = genre
			view.onDataReceived(getSortedFilmsByGenre(genre))
		} else if (activeGenre!!.name == genre.name) {
			activeGenre = null
			view.onDataReceived(films)
		}
	}

	override fun onRepeatLoad() {
		rescheduleJob()
	}

	override fun onItemClick(film: FilmEntity) {
		view.goToInfoFragment(film)
	}

	override fun onRestoredContent() {

		if (activeGenre != null) {
			view.onDataReceived(getSortedFilmsByGenre(activeGenre!!))
		} else if (films.any { it is FilmUiModel.Cover }) {
			view.onDataReceived(films)
		}
	}

	override fun getActiveGenre(): FilmGenre? {
		return activeGenre
	}

	override fun onSaveState(outState: Bundle) {
		outState.putSerializable("genre", activeGenre)
	}

	override fun onNewGenre(genre: FilmGenre) {
		activeGenre = genre
	}
}