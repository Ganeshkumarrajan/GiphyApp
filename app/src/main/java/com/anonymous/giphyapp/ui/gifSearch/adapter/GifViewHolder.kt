package com.anonymous.giphyapp.ui.gifSearch.adapter

import androidx.recyclerview.widget.RecyclerView
import com.anonymous.giphyapp.databinding.ImageAdapterLayoutBinding
import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize

class GifViewHolder(private val binding: ImageAdapterLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: AnimatedImagesInMultipleSize) {
        binding.imageData = data
    }
}
