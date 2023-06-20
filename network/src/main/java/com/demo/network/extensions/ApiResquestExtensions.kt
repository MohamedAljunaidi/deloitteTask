package com.demo.network.extensions

import com.demo.core.bases.ResultWrapper
import com.demo.network.result.NetworkResult

inline fun <DATA, DOMAIN> tryRequest(
    request: () -> NetworkResult<DATA>,
    dataToDomain: (DATA?) -> DOMAIN
) = try {
    when (val response = request()) {
        is NetworkResult.Success -> {
            ResultWrapper.Success(dataToDomain(response.data))
        }
        is NetworkResult.Error -> {
            ResultWrapper.Error(response.error)
        }
    }
} catch (e: Exception) {
    ResultWrapper.Error(e.parseErrorResponse())
}