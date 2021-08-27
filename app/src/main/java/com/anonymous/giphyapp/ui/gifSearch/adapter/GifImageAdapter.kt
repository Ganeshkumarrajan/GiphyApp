package com.anonymous.giphyapp.ui.gifSearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.anonymous.giphyapp.databinding.ImageAdapterLayoutBinding
import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize

class GifImageAdapter : ListAdapter<AnimatedImagesInMultipleSize, GifViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder =
        GifViewHolder(
            ImageAdapterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val REPO_COMPARATOR =
            object : DiffUtil.ItemCallback<AnimatedImagesInMultipleSize>() {
                override fun areItemsTheSame(
                    oldItem: AnimatedImagesInMultipleSize,
                    newItem: AnimatedImagesInMultipleSize
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: AnimatedImagesInMultipleSize,
                    newItem: AnimatedImagesInMultipleSize
                ): Boolean =
                    oldItem == newItem
            }
    }
}
