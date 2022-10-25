package com.feandrade.forecast.weather.domain.di.home

import com.feandrade.forecast.weather.data.repository.WeatherRepository
import com.feandrade.forecast.weather.data.repository.WeatherRepositoryImpl
import com.feandrade.forecast.weather.data.repositorynews.RepositoryNews
import com.feandrade.forecast.weather.data.repositorynews.RepositoryNewsImpl
import com.feandrade.forecast.weather.data.sharedpreference.DataStorage
import com.feandrade.forecast.weather.data.sharedpreference.SharedPreference
import com.feandrade.forecast.weather.presenter.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object HomeComponent : KoinComponent {

    private val repositoryModule = module {
        factory<WeatherRepository> {
            WeatherRepositoryImpl(get())
        }
    }

    private val provideApiModule = module {
        single {
            provideApi(get())
        }
    }

    private val provideNewsApiModule = module {
        single {
            provideNewsApi(get())
        }
    }

    private val homeViewModelModule = module {
        viewModel {
            HomeViewModel(get(),get(), get(), get())
        }
    }

    private val repositoryNewsModule = module {
        factory<RepositoryNews> {
            RepositoryNewsImpl(get())
        }
    }

    private val coroutineDispatcherIoModule = module {
        single {
            Dispatchers.IO
        }
    }

    private val dataStorageModule = module {
        factory<DataStorage> {
            SharedPreference(androidContext())
        }
    }

    fun inject() = loadKoinModules(
        homeModules
    )

    private val homeModules = listOf(
        repositoryModule,
        provideApiModule,
        homeViewModelModule,
        provideNewsApiModule,
        repositoryNewsModule,
        coroutineDispatcherIoModule,
        dataStorageModule
    )
}