package com.demo.nytimesapp.presentations.home

import android.os.Bundle
import android.view.View
import com.demo.core.bases.BaseFragment
import com.demo.nytimesapp.R
import com.demo.nytimesapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            viewBinding?.textHome?.text = it
        }

    }
}