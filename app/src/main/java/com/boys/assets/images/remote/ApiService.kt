package com.boys.assets.images.remote

import com.boys.assets.images.activity.photos.model.ImagesModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("list")
    suspend fun getList() : ArrayList<ImagesModel>

}