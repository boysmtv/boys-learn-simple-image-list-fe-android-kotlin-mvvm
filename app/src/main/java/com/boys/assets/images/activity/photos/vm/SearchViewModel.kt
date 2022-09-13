package com.boys.assets.images.activity.search.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boys.assets.images.activity.photos.model.ImagesModel
import com.boys.assets.images.activity.photos.usecase.ImagesUC
import com.boys.assets.images.domain.model.ApiError
import com.boys.assets.images.domain.usecase.UseCaseResponse

class SearchViewModel constructor(private val useCase: ImagesUC) : ViewModel()  {

    private val TAG = this::class.java.simpleName

    val onSuccess = MutableLiveData<ArrayList<ImagesModel>>()
    val onError = MutableLiveData<String>()

    fun doIt() {
        useCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<ArrayList<ImagesModel>> {
                override fun onSuccess(result: ArrayList<ImagesModel>) {
                    onSuccess.value = result
                }

                override fun onError(apiError: ApiError) {
                    onError.value = apiError.getErrorMessage()
                }
            },
        )
    }
}