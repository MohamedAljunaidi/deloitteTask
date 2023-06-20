package com.demo.nytimesapp.presentations.signup

import android.os.Bundle
import android.view.View
import com.demo.core.bases.BaseFragment
import com.demo.core.extensions.collectLatest
import com.demo.core.extensions.showDatePickerDialog
import com.demo.core.extensions.showToast
import com.demo.nytimesapp.R
import com.demo.nytimesapp.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataBinding()
        collectLatest(viewModel.signUpSuccess, ::handleSignUpSuccess)
        collectLatest(viewModel.state, ::handleViewState)
        collectLatest(viewModel.validationError, ::handleValidationError)
        viewBinding?.birthSignUpEditText?.setOnClickListener {
            requireContext().showDatePickerDialog(viewBinding?.birthSignUpEditText)
        }
    }

    private fun initDataBinding() {
        viewBinding?.viewModel = viewModel
    }

    private fun handleSignUpSuccess(isSignUp: Boolean?) {
        requireContext().showToast(getString(R.string.success_sign_up))
    }

    private fun handleValidationError(validationError: ValidationViewState) {
        ValidationFactory.validate(validationError, requireContext())
    }
}