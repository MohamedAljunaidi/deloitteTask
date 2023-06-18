package com.demo.nytimesapp.presentations.more

sealed class MoreViewState {

    object NavigateToSettings :MoreViewState()
    object LogoutUser:MoreViewState()
}
