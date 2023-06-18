package com.demo.nytimesapp.presentations.more

import com.demo.caching.manager.CachingManager
import com.demo.caching.manager.ProviderEnum
import com.demo.core.bases.BaseViewModel
import com.demo.core.bases.BaseViewState
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.domain.signup.usecases.GetSignUpDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val getSignUpDataUseCase: GetSignUpDataUseCase,
    private val cachingManager: CachingManager
) : BaseViewModel() {

    private var _personalDetailsSuccess: MutableSharedFlow<User?> =
        MutableSharedFlow(replay = 1)
    val personalDetailsSuccess: SharedFlow<User?> =
        _personalDetailsSuccess.asSharedFlow()

    private var _viewState: MutableSharedFlow<MoreViewState> =
        MutableSharedFlow()
    val viewState: SharedFlow<MoreViewState> =
        _viewState.asSharedFlow()

    fun getUserDetails() {
        launchCoroutine(coroutineExceptionHandler) {
            getSignUpDataUseCase(
                cachingManager.getProvider(ProviderEnum.PREFERENCES).getLoggedInUserEmail().first()
            )
                ?.onStart {
                    _state.emit(BaseViewState.Loading)
                }
                ?.onCompletion {
                    _state.emit(BaseViewState.DataLoaded)
                }?.collect { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            _personalDetailsSuccess.emit(result.data)
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

    fun doLogout() {
        launchCoroutine(coroutineExceptionHandler) {
            cachingManager.getProvider(ProviderEnum.PREFERENCES).setLoggedInUserEmail(null)
            _viewState.emit(MoreViewState.LogoutUser)
        }
    }

    fun navigateToSettings() {
        launchCoroutine(coroutineExceptionHandler) {
            _viewState.emit(MoreViewState.NavigateToSettings)
        }
    }


}