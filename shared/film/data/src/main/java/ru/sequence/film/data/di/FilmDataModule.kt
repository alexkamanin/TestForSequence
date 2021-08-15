package ru.sequence.film.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.sequence.film.data.api.FilmApi
import ru.sequence.film.data.datasource.FilmDataSource
import ru.sequence.film.data.datasource.FilmDataSourceImpl
import ru.sequence.film.data.repository.FilmRepositoryImpl
import ru.sequence.film.domain.repository.FilmRepository

internal val filmApi = module {

	single<FilmApi> { get<Retrofit>().create(FilmApi::class.java) }
}

internal val filmDataSource = module {
	factory<FilmDataSource> { FilmDataSourceImpl(get()) }
}

internal val filmRepository = module {
	factory<FilmRepository> { FilmRepositoryImpl(get()) }
}

val filmDataModule = listOf(
	filmApi,
	filmDataSource,
	filmRepository
)