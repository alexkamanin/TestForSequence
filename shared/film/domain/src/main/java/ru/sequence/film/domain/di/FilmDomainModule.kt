package ru.sequence.film.domain.di

import org.koin.dsl.module
import ru.sequence.film.domain.usecase.GetAllFilmsUseCase

val filmDomainModule = module {

	factory { GetAllFilmsUseCase(get()) }
}