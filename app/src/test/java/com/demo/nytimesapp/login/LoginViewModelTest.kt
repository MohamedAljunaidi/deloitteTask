package com.demo.nytimesapp.login

import app.cash.turbine.test
import com.demo.caching.manager.CachingManager
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.common.TestCoroutineRule
import com.demo.nytimesapp.domain.login.usecases.LoginUseCase
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.presentations.login.LoginViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

private val USER: User =
    User(
        "fullName", "email",
        "nat", "phone", "date", "password"
    )
private val SUCCESS_USER = ResultWrapper.Success(USER)

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: LoginViewModel

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var cachingManager: CachingManager

    @Before
    fun setUp() {
        sut = LoginViewModel(loginUseCase, cachingManager)
    }

    //doValidationTheLogin when called then should not Interactions
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test doValidationTheLogin when Validation success, login should called`() =
        runTest {
            prepareValidationSuccess()
            sut.doValidationTheLogin("email", "password")
            verifyNoMoreInteractions(loginUseCase)
        }

    //getMostPopular when success then emit success data
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test doLogin when caching success, doLogin should emit correct data`() =
        runTest {
            prepareValidationSuccess()
            sut.doLogin("", "")
            loginUseCase().test {
                assertEquals(SUCCESS_USER, awaitItem())
                awaitComplete()
            }
        }


    private suspend fun prepareValidationSuccess() {
        Mockito.`when`(loginUseCase())
            .thenReturn(flowOf(SUCCESS_USER))
    }
}