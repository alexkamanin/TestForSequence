package ru.sequence.film.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sequence.film.data.datasource.FilmDataSource
import ru.sequence.film.data.mapper.toEntity
import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.repository.FilmRepository

class FilmRepositoryImpl(private val dataSource: FilmDataSource) : FilmRepository {

	override suspend fun getAllFilms(): List<FilmEntity> = withContext(Dispatchers.IO) {
		dataSource.getAllFilms().map { it.toEntity() }
	}
}