package com.demo.nytimesapp.domain.signup.usecases

import com.demo.core.bases.BaseUseCase
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.domain.signup.repository.ISignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetSignUpDataUseCase @Inject constructor(
    private val signUpRepository: ISignUpRepository
) : BaseUseCase<User, Flow<ResultWrapper<Unit?>>?> {

    override suspend fun invoke(params: User?): Flow<ResultWrapper<Unit?>>? =
        params?.let {
            signUpRepository.insertUserData(
                user = it
            )
        }
}

