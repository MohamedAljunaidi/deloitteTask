package com.demo.nytimesapp.common

import com.demo.caching.roomdb.features.home.entities.MostPopularEntity
import com.demo.caching.roomdb.features.signup.entities.UserEntity
import com.demo.nytimesapp.domain.home.model.MostPopular
import com.demo.nytimesapp.domain.signup.model.User

internal fun User.toUserEntity(): UserEntity {
    return UserEntity(
        fullName = this.fullName,
        email = this.email,
        nationalId = this.nationalId,
        phoneNumber = this.phoneNumber,
        dateOfBirth = this.dateOfBirth,
        password = this.password,
    )
}

internal fun UserEntity.toUser(): User {
    return User(
        fullName = this.fullName,
        email = this.email,
        nationalId = this.nationalId,
        phoneNumber = this.phoneNumber,
        dateOfBirth = this.dateOfBirth,
        password = this.password
    )
}

internal fun List<MostPopularEntity>.toMostPopular(): List<MostPopular> {
    val mostPopularList = arrayListOf<MostPopular>()
    this.map {
        mostPopularList.add(
            MostPopular(
                title = it.title,
                id = it.id,
                newsImage = it.newsImage,
                publishTime = it.publishTime,
                url = it.url
            )
        )
    }
    return mostPopularList
}

internal fun List<MostPopular?>.toMostPopularEntity(): List<MostPopularEntity> {
    val mostPopularEntityList = arrayListOf<MostPopularEntity>()
    this.map { mostPopular ->
        mostPopularEntityList.add(
            MostPopularEntity(
                title = mostPopular?.title ?: "",
                id = mostPopular?.id ?: 0,
                newsImage = mostPopular?.newsImage ?: "",
                publishTime = mostPopular?.publishTime ?: "",
                url = mostPopular?.url ?: ""
            )
        )
    }
    return mostPopularEntityList
}

