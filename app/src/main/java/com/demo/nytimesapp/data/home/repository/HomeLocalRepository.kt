package com.demo.nytimesapp.data.home.repository

import com.demo.caching.manager.CachingManager
import com.demo.caching.manager.ProviderEnum
import com.demo.caching.util.tryMapperQuery
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.common.toMostPopular
import com.demo.nytimesapp.common.toMostPopularEntity
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.home.repository.IHomeLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeLocalRepository(private val cachingManager: CachingManager) :
    IHomeLocalRepository {

    override fun getMostPopulars(): Flow<ResultWrapper<List<MostPopular>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getMostPopulars()
        })
        { mostPopularEntity ->
            mostPopularEntity?.toMostPopular()
        }
        emit(result)
    }
    override fun getFilteredMostPopulars(searchQuery: String): Flow<ResultWrapper<List<MostPopular>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getFilteredMostPopulars(searchQuery)
        })
        { mostPopularEntity ->
            mostPopularEntity?.toMostPopular()
        }
        emit(result)
    }

    override fun insertMostPopulars(mostPopulars: List<MostPopular>?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                mostPopulars?.toMostPopularEntity()?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertMostPopulars(it)
                }
            }) {}
            emit(result)
        }


}
