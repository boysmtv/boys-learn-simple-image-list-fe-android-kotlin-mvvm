package com.boys.assets.images.domain.usecase

import com.boys.assets.images.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError)

}

