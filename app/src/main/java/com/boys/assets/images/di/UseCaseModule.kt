package com.boys.assets.images.di

import com.boys.assets.images.activity.photos.usecase.ImagesUC
import com.boys.assets.images.di.network.Repository

fun getSearchUseCase(repository: Repository): ImagesUC {
    return ImagesUC(repository)
}
