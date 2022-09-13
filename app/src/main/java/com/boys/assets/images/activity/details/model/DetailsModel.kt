package com.boys.assets.images.activity.details.model

import com.google.gson.annotations.SerializedName

data class DetailsModel (

    @SerializedName("firstname")
    var firstname: String? = null,

    @SerializedName("lastname")
    var lastname: String? = null,

    @SerializedName("comment")
    var comment: String? = null,

)