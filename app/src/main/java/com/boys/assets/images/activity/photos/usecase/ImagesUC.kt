package com.boys.assets.images.activity.photos.usecase

import com.boys.assets.images.activity.photos.model.ImagesModel
import com.boys.assets.images.di.network.Repository
import com.boys.assets.images.domain.usecase.UseCase

class ImagesUC constructor(
    private val repository: Repository
) : UseCase<ArrayList<ImagesModel>, Any?>() {

    private val TAG = this::class.java.simpleName

    override suspend fun run(params: Any?): ArrayList<ImagesModel> {
        return repository.postSearch()
    }

}