package com.hmtamim.imagesearch.utils

interface ResponseListener<T> {
    fun onSuccess(response: T, statusCode: Int)
    fun onError(errorBody: String, errorCode: Int)
}