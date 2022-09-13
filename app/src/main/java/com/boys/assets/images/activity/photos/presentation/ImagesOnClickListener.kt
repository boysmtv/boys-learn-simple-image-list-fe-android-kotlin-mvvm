package com.boys.assets.images.activity.photos.presentation

import android.view.View
import com.boys.assets.images.activity.photos.model.ImagesModel

interface ImagesOnClickListener<T> {
    fun onItemClick(v: View?, position: Int, model: ImagesModel)
}