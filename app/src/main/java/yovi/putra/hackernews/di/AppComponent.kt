package yovi.putra.hackernews.di

import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
}

val dataSourceModule = module {
}

val viewModelModule = module {
}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)