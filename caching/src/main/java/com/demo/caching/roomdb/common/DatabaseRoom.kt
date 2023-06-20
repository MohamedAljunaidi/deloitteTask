package com.demo.caching.roomdb.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.caching.roomdb.features.home.dao.MostPopularDao
import com.demo.caching.roomdb.features.home.entities.MostPopularEntity
import com.demo.caching.roomdb.features.signup.dao.UserDao
import com.demo.caching.roomdb.features.signup.entities.UserEntity

@Database(
    entities = [UserEntity::class, MostPopularEntity::class],
    version = RoomConstants.DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun mostPopularDao(): MostPopularDao
}
