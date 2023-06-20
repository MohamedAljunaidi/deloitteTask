package com.demo.network.services

import com.demo.network.extensions.getModel
import com.demo.network.extensions.map
import com.demo.network.extensions.safeApiCall
import com.demo.network.result.NetworkResult
import javax.inject.Inject

class ApiManager @Inject constructor(
    val services: ApiRequests,
) {

    suspend inline fun <reified T> getRequest(
        url: String,
        headersMap: Map<String, String>? = mapOf(),
        queryParamsMap: Map<String, Any?>? = mapOf()
    ): NetworkResult<T> =
        safeApiCall {
            services.getRequest(
                url = url,
                headersMap = headersMap,
                queryParamsMap = queryParamsMap
            )
        }.map { response ->
                response?.body()?.getModel()
            }
}