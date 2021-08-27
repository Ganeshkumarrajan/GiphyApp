package com.anonymous.giphyapp.data

data class GifImagesRemoteResponse(
    var data: List<AnimatedImageNetwork>,
    var meta: Meta,
    var pagination: Pagination
)

data class AnimatedImageNetwork(
    var id: String,
    var images: Images,
    var import_datetime: String,
    var trending_datetime: String,
    var type: String,
    var url: String
)

data class Images(
    var fixed_width: FixedWidth,
    var original: Original
)

data class Meta(
    var msg: String,
    var response_id: String,
    var status: Int
)

data class Pagination(
    var count: Int,
    var offset: Int,
    var total_count: Int
)

data class Original(
    var height: String,
    var size: String,
    var url: String,
    var width: String
)

data class FixedWidth(
    var height: String,
    var size: String,
    var url: String,
    var width: String
)
