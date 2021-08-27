package com.anonymous.giphyapp.domain.search.model

data class AnimatedImagesInMultipleSize(
    val id: String,
    val smallGifImage: AnimatedImage,
    val largeGifImage: AnimatedImage
)

data class AnimatedImage(val imageURl: String)

data class SearchParams(val query: String, val searchType: SearchType)

enum class SearchType {
    NEW,
    LOAD_MORE
}
