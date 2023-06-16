package com.demo.core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.demo.core.databinding.ToolbarLayoutBinding

class CustomToolbar : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)


    private var mBinding: ToolbarLayoutBinding? = null

    init {
        init()
    }

    private fun init() {
        mBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(context),
                R.layout.toolbar_layout,
                this,
                true
            ) as ToolbarLayoutBinding
    }

    fun setToolbarTitle(title: String?) {
        mBinding?.toolbar?.title = title
    }

    fun getToolBar(): Toolbar? {
        return mBinding?.toolbar
    }

    fun destroyView(){
        mBinding=null
    }
}