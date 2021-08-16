package ru.sequence.libraries.network.core.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sequence.libraries.network.core.BuildConfig

private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

val retrofitModule = module {

	single {
		Retrofit.Builder()
			.baseUrl(BuildConfig.BACKEND_ENDPOINT)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
	}
}