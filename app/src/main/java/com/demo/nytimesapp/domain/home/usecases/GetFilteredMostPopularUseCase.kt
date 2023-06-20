package com.demo.nytimesapp.domain.home.usecases

import com.demo.core.bases.BaseUseCase
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.home.repository.IHomeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredMostPopularUseCase @Inject constructor(
    private val localRepository: IHomeLocalRepository
) : BaseUseCase<String, Flow<ResultWrapper<List<MostPopular>?>>> {

    override suspend fun invoke(params: String?): Flow<ResultWrapper<List<MostPopular>?>> =
        localRepository.getFilteredMostPopulars(params ?: "")
}

