package com.anonymous.giphyapp.data.service

import com.anonymous.giphyapp.BuildConfig
import com.anonymous.giphyapp.data.GifImagesRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("gifs/search")
    suspend fun fetchAnimatedImages(
        @Query("api_key") key: String? = BuildConfig.GIPHY_KEY,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): GifImagesRemoteResponse
}
