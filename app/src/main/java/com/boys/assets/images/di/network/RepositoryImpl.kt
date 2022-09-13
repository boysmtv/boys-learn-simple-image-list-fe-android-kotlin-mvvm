package com.boys.assets.images.di.network

import com.boys.assets.images.activity.photos.model.ImagesModel
import com.boys.assets.images.remote.ApiService

class RepositoryImpl (private val apiService: ApiService) : Repository {

    override suspend fun postSearch(): ArrayList<ImagesModel> {
        return apiService.getList()
    }

}