package com.demo.nytimesapp.data.home.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MostPopularResponse(

    @SerializedName("copyright")
    val copyright: String? = "",

    @SerializedName("num_results")
    val numResults: Int? = 0,

    @SerializedName("results")
    val resultResponses: List<ResultResponse> = listOf(),

    @SerializedName("status")
    val status: String? = ""
)