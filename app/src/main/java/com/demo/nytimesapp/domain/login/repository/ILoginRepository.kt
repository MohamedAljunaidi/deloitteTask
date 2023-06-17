package com.demo.nytimesapp.domain.login.repository

import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.signup.model.User
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {

    fun doLogin(email: String, password: String): Flow<ResultWrapper<User?>>
}