package com.demo.nytimesapp.domain.home.repository

import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.home.model.MostPopular
import kotlinx.coroutines.flow.Flow

interface IHomeLocalRepository {

    fun getMostPopulars(): Flow<ResultWrapper<List<MostPopular>?>>
    fun insertMostPopulars(mostPopulars: List<MostPopular>?): Flow<ResultWrapper<Unit?>>
    fun getFilteredMostPopulars(searchQuery: String): Flow<ResultWrapper<List<MostPopular>?>>
}