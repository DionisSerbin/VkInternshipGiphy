package com.example.vkinternshipgiphy.data.model

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val `data`: List<GiphyData>,
)

data class GiphyData(
    @SerializedName("images")
    val images: Images
)

data class Images(
    @SerializedName("original")
    val original: Original
)

data class Original(
    @SerializedName("url")
    val url: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("width")
    val width: String
)