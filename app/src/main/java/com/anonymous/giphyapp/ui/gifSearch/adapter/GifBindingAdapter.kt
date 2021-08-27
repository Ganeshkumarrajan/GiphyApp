package com.anonymous.giphyapp.ui.gifSearch.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.anonymous.giphyapp.ui.gifSearch.views.SearchFragmentDirections
import com.bumptech.glide.Glide

@BindingAdapter("gifImageURL")
fun gifImage(imageView: ImageView, gifURL: String?) {
    gifURL?.let {
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }
}

@BindingAdapter("android:onClick")
fun onClick(view: View, url: String?) {
    view.setOnClickListener {
        url?.let {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(url)
            view.findNavController().navigate(action)
        }
    }
}
