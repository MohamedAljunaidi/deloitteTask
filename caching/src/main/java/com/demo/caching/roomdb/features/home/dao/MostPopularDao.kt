package com.demo.caching.roomdb.features.home.dao

import androidx.room.Dao
import androidx.room.Query
import com.demo.caching.roomdb.common.BaseDao
import com.demo.caching.roomdb.features.home.entities.MostPopularEntity
import com.demo.caching.roomdb.features.signup.entities.UserEntity

@Dao
interface MostPopularDao : BaseDao<MostPopularEntity> {

    @Query("SELECT * FROM MostPopularEntity")
    fun getMostPopular(): List<MostPopularEntity>

    @Query("SELECT * FROM MostPopularEntity WHERE title LIKE '%' || :searchQuery || '%'")
    fun getFilteredMostPopular(searchQuery : String): List<MostPopularEntity>


}
