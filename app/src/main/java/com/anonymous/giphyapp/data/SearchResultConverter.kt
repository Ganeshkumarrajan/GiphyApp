package com.anonymous.giphyapp.data

import com.anonymous.giphyapp.domain.search.model.AnimatedImage
import com.anonymous.giphyapp.domain.search.model.AnimatedImagesInMultipleSize

fun AnimatedImageNetwork.toDomain(): AnimatedImagesInMultipleSize =
    AnimatedImagesInMultipleSize(
        this.id,
        smallGifImage = AnimatedImage(
            this.images.fixed_width.url
        ),
        largeGifImage = AnimatedImage(
            this.images.original.url
        )
    )

fun GifImagesRemoteResponse.toDomain(): List<AnimatedImagesInMultipleSize> =
    this.data.map {
        it.toDomain()
    }
