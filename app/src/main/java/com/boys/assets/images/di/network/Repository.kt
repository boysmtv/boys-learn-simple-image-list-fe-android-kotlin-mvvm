package com.boys.assets.images.di.network

import com.boys.assets.images.activity.photos.model.ImagesModel

interface Repository {

    suspend fun postSearch(): ArrayList<ImagesModel>

}