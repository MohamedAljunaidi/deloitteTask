package com.demo.nytimesapp.domain.login.usecases

import com.demo.core.bases.BaseUseCase
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.common.UseCaseConstants
import com.demo.nytimesapp.domain.login.repository.ILoginRepository
import com.demo.nytimesapp.domain.signup.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: ILoginRepository
) : BaseUseCase<Map<String, String>, Flow<ResultWrapper<User?>>?> {

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<User?>> =
        loginRepository.doLogin(
            email = params?.get(UseCaseConstants.EMAIL).toString(),
            password = params?.get(UseCaseConstants.PASSWORD).toString()
        )
}

