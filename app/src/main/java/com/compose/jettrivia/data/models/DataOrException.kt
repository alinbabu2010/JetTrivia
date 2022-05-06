package com.compose.jettrivia.data.models

data class DataOrException<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var exception: E? = null
)
