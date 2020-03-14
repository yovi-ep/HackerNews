package yovi.putra.hackernews.di

import org.koin.core.module.Module
import org.koin.dsl.module
import yovi.putra.hackernews.core.services.RetrofitService
import yovi.putra.hackernews.data.remote.HackerNewsApi
import yovi.putra.hackernews.data.repository.HackerNewsRepository

val networkModule = module {
    single { RetrofitService.api<HackerNewsApi>() }
}

val dataSourceModule = module {
    single { HackerNewsRepository(get()) }
}

val viewModelModule = module {
}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)