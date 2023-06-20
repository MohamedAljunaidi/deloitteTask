package com.demo.nytimesapp.domain.home.repository

import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.home.model.MostPopular
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    fun getMostPopulars(key:String): Flow<ResultWrapper<List<MostPopular>?>>
}