package yovi.putra.hackernews.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import yovi.putra.hackernews.core.services.RetrofitService
import yovi.putra.hackernews.data.remote.HackerNewsApi
import yovi.putra.hackernews.data.repository.HackerNewsRepository
import yovi.putra.hackernews.features.dashboard.DashboardViewModel
import yovi.putra.hackernews.features.detail.StoryDetailViewModel
import yovi.putra.hackernews.features.favorite.FavoriteViewModel

val networkModule = module {
    single { RetrofitService.api<HackerNewsApi>() }
}

val dataSourceModule = module {
    single { HackerNewsRepository(get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { StoryDetailViewModel(get()) }
    viewModel { FavoriteViewModel() }
}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)