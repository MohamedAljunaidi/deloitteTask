package com.demo.nytimesapp.presentations.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.demo.core.bases.BaseListAdapter
import com.demo.core.bases.BaseViewHolder
import com.demo.nytimesapp.R
import com.demo.nytimesapp.databinding.ItemMostPopulerBinding
import com.demo.nytimesapp.domain.home.model.MostPopular

class MostPopularAdapter : BaseListAdapter<BaseViewHolder<MostPopular>, MostPopular>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemMostPopulerBinding>(
            inflater, R.layout.item_most_populer, parent, false
        )
        return MostPopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MostPopular>, position: Int) {
        holder.bind(getItem(position), position, getOnItemClickListener())
    }

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<MostPopular> =
            object : DiffUtil.ItemCallback<MostPopular>() {
                override fun areContentsTheSame(
                    oldItem: MostPopular,
                    newItem: MostPopular
                ): Boolean {
                    return oldItem.title == newItem.title
                }

                override fun areItemsTheSame(
                    oldItem: MostPopular,
                    newItem: MostPopular
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}