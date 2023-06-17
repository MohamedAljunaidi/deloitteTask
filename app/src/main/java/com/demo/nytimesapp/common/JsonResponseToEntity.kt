package com.demo.nytimesapp.common

import com.demo.caching.roomdb.features.signup.entities.UserEntity
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