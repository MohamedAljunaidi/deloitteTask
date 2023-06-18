package com.demo.nytimesapp.presentations.more

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.demo.core.bases.BaseFragment
import com.demo.core.extensions.collectLatest
import com.demo.nytimesapp.R
import com.demo.nytimesapp.databinding.FragmentMoreBinding
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.presentations.PreLoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>(R.layout.fragment_more) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserDetails()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataBinding()
        collectLatest(viewModel.personalDetailsSuccess, ::handleUserDetailsSuccess)
        collectLatest(viewModel.state, ::handleViewState)
        viewLifecycleOwner.collectLatest(viewModel.viewState, ::handleMoreState)
    }

    private fun initDataBinding() {
        viewBinding?.viewModel = viewModel
    }

    private fun handleUserDetailsSuccess(user: User?) {
        viewBinding?.user = user
    }

    private fun handleMoreState(viewState: MoreViewState?) {
        when (viewState) {
            is MoreViewState.LogoutUser -> {
                startActivity(Intent(requireActivity(), PreLoginActivity::class.java))
                requireActivity().finish()
            }
            is MoreViewState.NavigateToSettings -> {
                findNavController().navigate(MoreFragmentDirections.actionHomeFragmentToSettingFragment())
            }
            else -> {}
        }
    }
}