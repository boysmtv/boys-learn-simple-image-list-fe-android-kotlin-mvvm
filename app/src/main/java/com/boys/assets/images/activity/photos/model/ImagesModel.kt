package com.boys.assets.images.activity.photos.model

import com.google.gson.annotations.SerializedName

data class ImagesModel (

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("width")
    var width: Int? = null,

    @SerializedName("height")
    var height: Int? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("download_url")
    var download_url: String? = null,

)