package com.demo.nytimesapp.common

import com.demo.nytimesapp.data.home.model.ResultResponse
import com.demo.nytimesapp.domain.home.model.MostPopular

internal fun List<ResultResponse>.toMostPopular(): List<MostPopular> {

    val mostPopularList = arrayListOf<MostPopular>()

    this.forEach { resultResponse ->
        mostPopularList.add(
            MostPopular(
                resultResponse.title ?: "",
                id = resultResponse.id ?: 0,
                newsImage = if (! resultResponse.mediaResponses.isNullOrEmpty()) resultResponse.mediaResponses[0]?.mediaMetadata?.get(
                    2
                )?.url ?: "" else "",
                publishTime = resultResponse.publishedDate ?: "",
                url = resultResponse.url ?: ""
            )
        )
    }
    return mostPopularList
}

