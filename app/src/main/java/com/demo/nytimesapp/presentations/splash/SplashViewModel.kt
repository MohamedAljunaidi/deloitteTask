package com.demo.nytimesapp.presentations.splash

import com.demo.caching.manager.CachingManager
import com.demo.caching.manager.ProviderEnum
import com.demo.core.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val cachingManager: CachingManager
) : BaseViewModel() {

    private var _navigateViewState: MutableSharedFlow<SplashNavigationViewState> =
        MutableSharedFlow(replay = 1)
    val navigateViewState: SharedFlow<SplashNavigationViewState> =
        _navigateViewState.asSharedFlow()

     fun isUserLoggedIn(
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            cachingManager.getProvider(ProviderEnum.PREFERENCES).getLoggedInUserEmail()
                .collectLatest {email->
                    if (email.isNullOrEmpty()){
                        _navigateViewState.emit(SplashNavigationViewState.NavigateToLogin)
                    }else{
                        _navigateViewState.emit(SplashNavigationViewState.NavigateToMain)
                    }
                }
        }
    }
}