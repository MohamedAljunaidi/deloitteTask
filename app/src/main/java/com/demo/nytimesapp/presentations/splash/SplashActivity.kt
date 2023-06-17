package com.demo.nytimesapp.presentations.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.demo.core.extensions.collectLatest
import com.demo.nytimesapp.presentations.MainActivity
import com.demo.nytimesapp.presentations.PreLoginActivity
import com.demo.nytimesapp.presentations.signup.ValidationFactory
import com.demo.nytimesapp.presentations.signup.ValidationViewState
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isUserLoggedIn()
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                collectLatest(viewModel.navigateViewState, ::handleNavigation)
            }, 2000)
        }
    }

    private fun handleNavigation(splashNavigationViewState: SplashNavigationViewState) {
        when (splashNavigationViewState) {
            is SplashNavigationViewState.NavigateToMain -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            is SplashNavigationViewState.NavigateToLogin -> {
                val intent = Intent(this, PreLoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }

        }
    }
}