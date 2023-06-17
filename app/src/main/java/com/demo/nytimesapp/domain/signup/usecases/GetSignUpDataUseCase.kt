package com.demo.nytimesapp.domain.signup.usecases

import com.demo.core.bases.BaseUseCase
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.domain.signup.repository.ISignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSignUpDataUseCase @Inject constructor(
    private val signUpRepository: ISignUpRepository
) : BaseUseCase<String, Flow<ResultWrapper<User?>>?> {

    override suspend fun invoke(params: String?): Flow<ResultWrapper<User?>>? =
        params?.let {
            signUpRepository.getUserData(
                email = params
            )
        }
}

