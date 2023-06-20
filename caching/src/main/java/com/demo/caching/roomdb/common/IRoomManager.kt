package com.demo.caching.roomdb.common

import com.demo.caching.roomdb.features.home.entities.MostPopularEntity
import com.demo.caching.roomdb.features.signup.entities.UserEntity
import com.demo.core.bases.ResultWrapper

interface IRoomManager {

    suspend fun insertUserData(userEntity: UserEntity): ResultWrapper<Unit>? = null
    suspend fun getUserData(email: String): ResultWrapper<UserEntity>? = null
    suspend fun login(email: String,password: String): ResultWrapper<UserEntity>? = null

    suspend fun insertMostPopulars(mostPopularEntity: List<MostPopularEntity>): ResultWrapper<Unit>? = null
    suspend fun getMostPopulars(): ResultWrapper<List<MostPopularEntity>>? = null
    suspend fun getFilteredMostPopulars(searchQuery:String): ResultWrapper<List<MostPopularEntity>>? = null
}