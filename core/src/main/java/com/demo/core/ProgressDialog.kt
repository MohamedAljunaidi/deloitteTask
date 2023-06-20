package com.demo.core

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.demo.core.databinding.DialogProgressBinding

class ProgressDialog(private val mContext: Context) : AppCompatDialog(mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: DialogProgressBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                mContext
            ), R.layout.dialog_progress, null, false
        )
        setContentView(binding.root)

        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}
