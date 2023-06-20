package com.demo.nytimesapp.home

import app.cash.turbine.test
import com.demo.core.bases.ResultException
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.common.TestCoroutineRule
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.home.usecases.GetFilteredMostPopularUseCase
import com.demo.nytimesapp.domain.home.usecases.GetMostPopularUseCase
import com.demo.nytimesapp.presentations.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

private val MOST_POPULAR_LIST: ArrayList<MostPopular> = arrayListOf(
    MostPopular(
        "title", "newsImage",
        "publishTime", "url", 1
    )
)
private val SUCCESS_MOST_POPULAR = ResultWrapper.Success(MOST_POPULAR_LIST)

private val ERROR_MOST_POPULAR =
    ResultWrapper.Error(ResultException(cause = Throwable("ERROR")))

private const val EXCEPTION = "EXCEPTION"

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: HomeViewModel

    @Mock
    private lateinit var mostPopularUseCaseMock: GetMostPopularUseCase

    @Mock
    private lateinit var filteredMostPopularUseCaseMock: GetFilteredMostPopularUseCase

    @Before
    fun setUp() {
        sut = HomeViewModel(mostPopularUseCaseMock, filteredMostPopularUseCaseMock)
    }

    //getMostPopular when success then emit success data
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopular when api success, mostPopularSuccess should emit correct data`() =
        runTest {
            prepareSuccess()
            sut.getMostPopularService()
            mostPopularUseCaseMock().test {
                assertEquals(SUCCESS_MOST_POPULAR, awaitItem())
                awaitComplete()
            }
        }


    //getMostPopular when called then should not Interactions
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopular when called, getMostPopular Should Not Interactions`() =
        runTest {
            prepareSuccess()
            sut.getMostPopularService()
            Mockito.verifyNoMoreInteractions(mostPopularUseCaseMock)
        }

    //getMostPopular when called then should Called Once
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopular when called, getMostPopular Should Called Once`() =
        runTest {
            mostPopularUseCaseMock()
            Mockito.verify(mostPopularUseCaseMock, Mockito.times(1))()
        }


    //getMostPopular when failure then emit failure state
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopular when api error, state should emit error data`() = runTest {
        prepareFailure()
        sut.getMostPopularService()
        mostPopularUseCaseMock().test {
            assertEquals(ERROR_MOST_POPULAR, awaitItem())
            awaitComplete()
        }
    }

    //getMostPopular when exception then emit exception data
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopular when api get exception, state should emit exception data`() = runTest {
        prepareCoroutineException()
        sut.getMostPopularService()
        mostPopularUseCaseMock().test {
            assertEquals(EXCEPTION, awaitError().message)
        }
    }


    //getMostPopularFilter when success then emit success data
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopularFilter when api success, mostPopularSuccess should emit correct data`() =
        runTest {
            prepareFilterSuccess()
            sut.getFilteredMostPopularUseCase("s")
            filteredMostPopularUseCaseMock().test {
                assertEquals(SUCCESS_MOST_POPULAR, awaitItem())
                awaitComplete()
            }
        }

    //getMostPopulaFilterr when called then should not Interactions
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopularFilterr when called, getMostPopularFilterr Should Not Interactions`() =
        runTest {
            prepareFilterSuccess()
            sut.getMostPopularService()
            Mockito.verifyNoMoreInteractions(filteredMostPopularUseCaseMock)
        }


    //getMostPopularFilter when called then should Called Once
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopularFilter when called, getMostPopularFilter Should Called Once`() =
        runTest {
            filteredMostPopularUseCaseMock()
            Mockito.verify(filteredMostPopularUseCaseMock, Mockito.times(1))()
        }


    //getMostPopularFilter when failure then emit failure state
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopularFilter when api error, state should emit error data`() = runTest {
        prepareFilterListFailure()
        sut.getMostPopularService()
        filteredMostPopularUseCaseMock().test {
            assertEquals(ERROR_MOST_POPULAR, awaitItem())
            awaitComplete()
        }
    }

    //getMostPopularFilter when exception then emit exception data
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getMostPopularFilter when api get exception, state should emit exception data`() =
        runTest {
            prepareFilterListCoroutineException()
            sut.getMostPopularService()
            filteredMostPopularUseCaseMock().test {
                assertEquals(EXCEPTION, awaitError().message)
            }
        }

    private suspend fun prepareCoroutineException() {
        Mockito.`when`(mostPopularUseCaseMock())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(mostPopularUseCaseMock())
            .thenReturn(flowOf(SUCCESS_MOST_POPULAR))
    }

    private suspend fun prepareFailure() {
        Mockito.`when`(mostPopularUseCaseMock())
            .thenReturn(flowOf(ERROR_MOST_POPULAR))
    }

    private suspend fun prepareFilterSuccess() {
        Mockito.`when`(filteredMostPopularUseCaseMock())
            .thenReturn(flowOf(SUCCESS_MOST_POPULAR))
    }

    private suspend fun prepareFilterListFailure() {
        Mockito.`when`(filteredMostPopularUseCaseMock())
            .thenReturn(flowOf(ERROR_MOST_POPULAR))
    }

    private suspend fun prepareFilterListCoroutineException() {
        Mockito.`when`(filteredMostPopularUseCaseMock())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }
}