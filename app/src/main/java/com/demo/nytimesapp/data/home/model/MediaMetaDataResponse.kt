package com.demo.nytimesapp.data.home.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MediaMetaDataResponse(
    @SerializedName("format")
    val format: String? = "",

    @SerializedName("height")
    val height: Int? = 0,

    @SerializedName("url")
    val url: String? = "",

    @SerializedName("width")
    val width: Int? = 0
)