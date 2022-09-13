package com.boys.assets.images.di

import com.boys.assets.images.remote.ApiService
import com.boys.assets.images.di.network.Repository
import com.boys.assets.images.di.network.RepositoryImpl

fun createRepository(apiService: ApiService): Repository {
    return RepositoryImpl(apiService)
}