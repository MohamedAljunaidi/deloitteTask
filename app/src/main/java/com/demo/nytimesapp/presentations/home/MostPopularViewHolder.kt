package com.demo.nytimesapp.presentations.home

import android.view.View
import com.demo.core.bases.BaseViewHolder
import com.demo.core.extensions.toViewClickListener
import com.demo.nytimesapp.databinding.ItemMostPopulerBinding
import com.demo.nytimesapp.domain.home.model.MostPopular

class MostPopularViewHolder(private val item: ItemMostPopulerBinding) : BaseViewHolder<MostPopular>(item) {

    override fun bind(
        model: MostPopular, position: Int, clickListener: ((view: View, model: MostPopular, position: Int) -> Unit)?
    ) {
        item.mostPopular = model
        item.clickListener = clickListener.toViewClickListener(model, position)
    }
}