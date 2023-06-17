package com.demo.nytimesapp.domain.signup.repository

import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.signup.model.User
import kotlinx.coroutines.flow.Flow

interface ISignUpRepository {

    fun getUserData(email:String): Flow<ResultWrapper<User?>>
    fun insertUserData(user: User): Flow<ResultWrapper<Unit?>>
}