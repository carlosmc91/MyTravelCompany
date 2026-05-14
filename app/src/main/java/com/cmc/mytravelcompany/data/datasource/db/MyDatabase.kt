package com.cmc.mytravelcompany.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmc.mytravelcompany.data.datasource.db.dao.BannerDao
import com.cmc.mytravelcompany.data.datasource.db.entity.BannerDbEntity

@Database(entities = [BannerDbEntity::class], version = 2, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerDao
}
