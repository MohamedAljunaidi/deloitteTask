package com.demo.nytimesapp.presentations.home

import com.demo.core.bases.BaseViewModel
import com.demo.core.bases.BaseViewState
import com.demo.core.bases.ResultWrapper
import com.demo.network.BuildConfig
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.home.usecases.GetFilteredMostPopularUseCase
import com.demo.nytimesapp.domain.home.usecases.GetMostPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mostPopularUseCase: GetMostPopularUseCase,
    private val filteredMostPopularUseCase: GetFilteredMostPopularUseCase
) : BaseViewModel() {

    private var sort = SortType.DESC
    private var mostPopulars: ArrayList<MostPopular>? = arrayListOf()

    private var _mostPopularSuccess: MutableSharedFlow<ArrayList<MostPopular>?> =
        MutableSharedFlow(replay = 1)

    val mostPopularSuccess: SharedFlow<ArrayList<MostPopular>?> =
        _mostPopularSuccess.asSharedFlow()

    private val _isPullToRefresh: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isPullToRefresh = _isPullToRefresh.asStateFlow()

    fun getMostPopularService(isPullToRefresh: Boolean = false) {
        launchCoroutine(coroutineExceptionHandler) {
            mostPopularUseCase(BuildConfig.API_KEY)
                .onStart {
                    if (isPullToRefresh) {
                        _isPullToRefresh.emit(true)
                    } else {
                        _state.emit(BaseViewState.Loading)
                    }
                }
                .onCompletion {
                    if (isPullToRefresh) {
                        _isPullToRefresh.emit(false)
                    } else {
                        _state.emit(BaseViewState.DataLoaded)
                    }
                }
                .collectLatest { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            mostPopulars = result.data?.sortedByDescending { it.publishTime }
                                ?.toCollection(ArrayList())
                            _mostPopularSuccess.emit(mostPopulars)
                        }
                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )
                        }

                    }
                }
        }
    }

    fun getFilteredMostPopularUseCase(searchQuery: String?) {
        launchCoroutine(coroutineExceptionHandler) {
            filteredMostPopularUseCase(searchQuery)
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }
                .onCompletion {
                    _state.emit(BaseViewState.DataLoaded)
                }
                .collectLatest { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            mostPopulars = if (sort == SortType.DESC) {
                                result.data?.sortedByDescending { it.publishTime }
                                    ?.toCollection(ArrayList())
                            } else {
                                result.data?.sortedBy { it.publishTime }
                                    ?.toCollection(ArrayList())
                            }
                            _mostPopularSuccess.emit(mostPopulars)
                        }
                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )
                        }

                    }
                }
        }
    }

    fun getSortedMostPopularList(sort: SortType): ArrayList<MostPopular>? {
        this.sort = sort
        return if (sort == SortType.DESC) {
            mostPopulars?.sortedByDescending { it.publishTime }
                ?.toCollection(ArrayList())
        } else {
            mostPopulars?.sortedBy { it.publishTime }
                ?.toCollection(ArrayList())
        }
    }

}

