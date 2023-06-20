package com.demo.nytimesapp.domain.home.usecases

import com.demo.core.bases.BaseUseCase
import com.demo.core.bases.ResultWrapper
import com.demo.core.extensions.networkBoundResource
import com.demo.core.extensions.resultWrapperData
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.home.repository.IHomeLocalRepository
import com.demo.nytimesapp.domain.home.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class GetMostPopularUseCase @Inject constructor(
    private val remoteRepository: IHomeRepository,
    private val localRepository: IHomeLocalRepository
) : BaseUseCase<String, Flow<ResultWrapper<List<MostPopular>?>>> {

    private var response: Flow<ResultWrapper<List<MostPopular>?>> = emptyFlow()

    override suspend fun invoke(params: String?): Flow<ResultWrapper<List<MostPopular>?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getMostPopulars()
            },
            fetchApi = {
                remoteRepository.getMostPopulars(key = params ?: "")
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { mostPopulars ->
                        localRepository.insertMostPopulars(
                            mostPopulars = mostPopulars
                        ).collect()
                    }, {
                        localRepository.getMostPopulars()
                    })
                }
            }, onQueryDbError = {
                response
            }
        )
}

