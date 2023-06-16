package com.demo.core.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import com.demo.core.extensions.getGenericClass

/**
 *  Main base [Fragment]
 *
 *
 *  @param B the type of dataBinding
 *  @param VM the type of ViewModel
 */
abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    var viewBinding: B? = null


    protected val viewModel: VM by createViewModelLazy(getGenericClass<VM>(1).kotlin, { viewModelStore })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding?.lifecycleOwner = viewLifecycleOwner

        return viewBinding?.root
    }

}
