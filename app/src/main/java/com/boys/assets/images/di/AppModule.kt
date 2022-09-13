package com.boys.assets.images.di

import com.boys.assets.images.activity.search.vm.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureStag = module {
    // for search
    viewModel { SearchViewModel(get()) }
    single { getSearchUseCase(get()) }

    // create repository
    single { createRepository(get()) }
}
