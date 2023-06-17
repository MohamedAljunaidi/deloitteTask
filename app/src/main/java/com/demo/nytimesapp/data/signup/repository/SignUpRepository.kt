package com.demo.nytimesapp.data.signup.repository

import com.demo.caching.manager.CachingManager
import com.demo.caching.manager.ProviderEnum
import com.demo.caching.util.tryMapperQuery
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.common.toUser
import com.demo.nytimesapp.common.toUserEntity
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.domain.signup.repository.ISignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpRepository(private val cachingManager: CachingManager) :
    ISignUpRepository {

    override fun getUserData(email: String): Flow<ResultWrapper<User?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getUserData(email)
        })
        { userEntity ->
            userEntity?.toUser()
        }
        emit(result)
    }

    override fun insertUserData(user: User): Flow<ResultWrapper<Unit?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM)
                .insertUserData(user.toUserEntity())
        }) {}
        emit(result)
    }
}
