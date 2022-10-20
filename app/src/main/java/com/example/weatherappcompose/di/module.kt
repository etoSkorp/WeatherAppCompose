package com.example.weatherappcompose.di

import androidx.lifecycle.SavedStateHandle
import com.example.weatherappcompose.BASE_URL
import com.example.weatherappcompose.data.SearchApi
import com.example.weatherappcompose.data.WeatherApi
import com.example.weatherappcompose.data.WeatherRepo
import com.example.weatherappcompose.data.WeatherRepoImpl
import com.example.weatherappcompose.ui.WeatherInteractor
import com.example.weatherappcompose.ui.WeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }

    single<SearchApi> { get<Retrofit>().create(SearchApi::class.java) }

    single<WeatherRepo> { WeatherRepoImpl(get<WeatherApi>(), get<SearchApi>()) }

    single<WeatherInteractor> { WeatherInteractor(weatherRepo = get<WeatherRepo>()) }

    viewModel<WeatherViewModel> { WeatherViewModel(SavedStateHandle(), get<WeatherInteractor>())}
}