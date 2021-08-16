package ru.sequence.film.domain.usecase

import ru.sequence.film.domain.entity.FilmEntity
import ru.sequence.film.domain.repository.FilmRepository

class GetAllFilmsUseCase(private val repository: FilmRepository) {

	suspend operator fun invoke(): List<FilmEntity> = repository.getAllFilms()
}