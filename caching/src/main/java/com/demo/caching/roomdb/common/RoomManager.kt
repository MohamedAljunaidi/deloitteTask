package com.demo.caching.roomdb.common

import com.demo.caching.manager.BaseManager
import com.demo.caching.roomdb.features.home.entities.MostPopularEntity
import com.demo.caching.roomdb.features.signup.entities.UserEntity
import com.demo.caching.util.safeLocalDataCall
import com.demo.core.bases.ResultWrapper
import javax.inject.Inject

class RoomManager @Inject constructor(private val databaseRoom: DatabaseRoom) : BaseManager {

    override suspend fun insertUserData(userEntity: UserEntity): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.userDao().insert(userEntity) }

    override suspend fun getUserData(email: String) =
        safeLocalDataCall { databaseRoom.userDao().getUserDataEntity(email) }

    override suspend fun login(email: String, password: String) =
        safeLocalDataCall { databaseRoom.userDao().login(email, password) }

    override suspend fun insertMostPopulars(mostPopularEntity: List<MostPopularEntity>): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.mostPopularDao().insert(mostPopularEntity) }

    override suspend fun getMostPopulars() =
        safeLocalDataCall { databaseRoom.mostPopularDao().getMostPopular() }

    override suspend fun getFilteredMostPopulars(searchQuery:String) =
        safeLocalDataCall { databaseRoom.mostPopularDao().getFilteredMostPopular(searchQuery) }

}