package com.demo.nytimesapp.presentations.home

import android.os.Bundle
import android.view.View
import com.demo.core.bases.BaseFragment
import com.demo.core.extensions.collectLatest
import com.demo.core.extensions.onEditTextChanged
import com.demo.core.extensions.openMenu
import com.demo.core.extensions.openWebUrl
import com.demo.nytimesapp.R
import com.demo.nytimesapp.databinding.FragmentHomeBinding
import com.demo.nytimesapp.domain.home.model.MostPopular
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private val mostPopularAdapter by lazy { MostPopularAdapter() }
    private var mostPopulars: ArrayList<MostPopular>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMostPopularService()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDataBinding()

        initViewListener()

        collectLatest(viewModel.mostPopularSuccess, ::handleMostPopularSuccess)
        collectLatest(viewModel.state, ::handleViewState)
    }

    private fun initDataBinding() {
        viewBinding?.adapter = mostPopularAdapter
        viewBinding?.viewModel = viewModel
    }

    private fun initViewListener() {
        mostPopularAdapter.setOnItemClickListener { _, model, _ ->
            requireContext().openWebUrl(model.url)
        }

        viewBinding?.refreshLayoutParkingAvailability?.setOnRefreshListener {
            viewBinding?.mSearchEditText?.setText("")
            viewModel.getMostPopularService(isPullToRefresh = true)
        }

        viewBinding?.mSearchEditText?.onEditTextChanged { text ->
            viewModel.getFilteredMostPopularUseCase(text)
        }

        viewBinding?.textViewSort?.setOnClickListener {
            openSortMenu()
        }
    }

    private fun openSortMenu() {
        requireContext().openMenu(
            viewBinding?.textViewSort,
            R.menu.sort_menu,
        ) { id ->
            when (id) {
                R.id.sort_asc_item -> {
                    viewBinding?.mostPopulars =  viewModel.getSortedMostPopularList(SortType.ASC)
                }
                R.id.sort_desc_item -> {
                    viewBinding?.mostPopulars =   viewModel.getSortedMostPopularList(SortType.DESC)
                }
            }
        }
    }

    private fun handleMostPopularSuccess(mostPopulars: ArrayList<MostPopular>?) {
        this.mostPopulars =
            mostPopulars?.toCollection(ArrayList())
        viewBinding?.mostPopulars = this.mostPopulars
    }

}