package com.demo.nytimesapp.presentations.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.demo.core.bases.BaseFragment
import com.demo.core.extensions.collectLatest
import com.demo.core.extensions.showToast
import com.demo.nytimesapp.R
import com.demo.nytimesapp.databinding.FragmentLoginBinding
import com.demo.nytimesapp.presentations.MainActivity
import com.demo.nytimesapp.presentations.signup.ValidationFactory
import com.demo.nytimesapp.presentations.signup.ValidationViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataBinding()
        collectLatest(viewModel.loginSuccess, ::handleLoginSuccess)
        collectLatest(viewModel.state, ::handleViewState)
        collectLatest(viewModel.validationError, ::handleValidationError)
    }

    private fun initDataBinding() {
        viewBinding?.viewModel = viewModel
    }

    private fun handleLoginSuccess(loggedIn: Boolean?) {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    private fun handleValidationError(validationError: ValidationViewState) {
        ValidationFactory.validate(validationError, requireContext())
    }
}