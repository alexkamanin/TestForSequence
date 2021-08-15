package ru.sequence.enterprise

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.sequence.film.data.di.filmDataModule
import ru.sequence.film.domain.di.filmDomainModule
import ru.sequence.libraries.network.core.di.retrofitModule
import ru.sequence.list.di.filmListModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			androidFileProperties()

			modules(retrofitModule)
			modules(filmDataModule)
			modules(filmDomainModule)
			modules(filmListModule)
		}
	}
}