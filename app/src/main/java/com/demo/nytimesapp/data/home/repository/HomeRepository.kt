package com.demo.nytimesapp.data.home.repository

import com.demo.core.bases.ResultWrapper
import com.demo.network.extensions.tryRequest
import com.demo.nytimesapp.common.toMostPopular
import com.demo.nytimesapp.data.home.HomeService
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.home.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository(private val homeService: HomeService) : IHomeRepository {

    override fun getMostPopulars(key: String): Flow<ResultWrapper<List<MostPopular>?>> = flow {

        val result = tryRequest(
            request = {
                homeService.getMostPopular(key)
            },
            dataToDomain = { response ->
                response?.resultResponses?.toMostPopular()
            }
        )
        emit(result)
    }
}