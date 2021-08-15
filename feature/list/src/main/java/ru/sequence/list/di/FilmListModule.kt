package ru.sequence.list.di

import org.koin.dsl.module
import ru.sequence.list.presenter.FilmPresenter
import ru.sequence.list.presenter.FilmPresenterImpl
import ru.sequence.list.ui.FilmListView

val filmListModule = module {
	factory<FilmPresenter> { (view: FilmListView) -> FilmPresenterImpl(view, get()) }
}