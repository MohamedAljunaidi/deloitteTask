package com.demo.nytimesapp.home

import com.demo.core.bases.ResultWrapper
import com.demo.network.common.NetworkException
import com.demo.network.result.NetworkResult
import com.demo.nytimesapp.common.TestCoroutineRule
import com.demo.nytimesapp.common.toMostPopular
import com.demo.nytimesapp.data.home.HomeService
import com.demo.nytimesapp.data.home.model.MostPopularResponse
import com.demo.nytimesapp.data.home.repository.HomeRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

private val SUCCESS_MOST_POPULAR = MostPopularResponse(resultResponses = listOf())


private val ERROR_HOME = NetworkException(cause = Throwable("ERROR"))
private const val KEY = "KEY"

@RunWith(MockitoJUnitRunner::class)
class HomeRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: HomeRepository

    @Mock
    private lateinit var homeService: HomeService

    @Before
    fun setUp() {
        sut = HomeRepository(homeService)
    }

    //getMostPopulars when success then emit success data
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopulars when api success, getMostPopulars should emit success data`() =
        runTest {
            prepareSuccess()
            val result = sut.getMostPopulars(KEY)
            assertEquals(
                ResultWrapper.Success(SUCCESS_MOST_POPULAR.resultResponses.toMostPopular()),
                result.single()
            )
        }

    //getMostPopulars when failure then emit failure state
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopulars when api failure, getMostPopulars should emit failure data`() {
        runTest {
            prepareError()
            val result = sut.getMostPopulars(KEY)
            assertEquals(ResultWrapper.Error(ERROR_HOME), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(homeService.getMostPopular(KEY))
            .thenReturn(NetworkResult.Success(SUCCESS_MOST_POPULAR))
    }

    private suspend fun prepareError() {
        Mockito.`when`(homeService.getMostPopular(KEY))
            .thenReturn(NetworkResult.Error(ERROR_HOME))
    }
}