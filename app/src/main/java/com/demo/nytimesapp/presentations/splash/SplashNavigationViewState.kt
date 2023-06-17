package com.demo.nytimesapp.presentations.splash

sealed class SplashNavigationViewState {

    object NavigateToMain : SplashNavigationViewState()
    object NavigateToLogin : SplashNavigationViewState()
}
