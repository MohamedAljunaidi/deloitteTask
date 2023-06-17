package com.demo.nytimesapp.data.login

import com.demo.caching.manager.CachingManager
import com.demo.caching.manager.ProviderEnum
import com.demo.caching.util.tryMapperQuery
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.common.toUser
import com.demo.nytimesapp.common.toUserEntity
import com.demo.nytimesapp.domain.login.repository.ILoginRepository
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.domain.signup.repository.ISignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepository(private val cachingManager: CachingManager) :
    ILoginRepository {

    override fun doLogin(email: String, password: String): Flow<ResultWrapper<User?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).login(email,password)
        })
        { userEntity ->
            userEntity?.toUser()
        }
        emit(result)
    }
}
