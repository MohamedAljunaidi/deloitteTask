package com.demo.network.result

import com.demo.network.common.NetworkException

sealed class NetworkResult<out T> {

    data class Success<T>(val data: T?) : NetworkResult<T>()

    data class Error(val error: NetworkException) : NetworkResult<Nothing>()

}