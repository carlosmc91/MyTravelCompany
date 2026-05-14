package com.cmc.mytravelcompany.data.di

import android.content.Context
import androidx.room.Room
import com.cmc.mytravelcompany.data.datasource.db.MyDatabase
import com.cmc.mytravelcompany.data.datasource.db.dao.BannerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "my_travel_company_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBannerDao(db: MyDatabase): BannerDao = db.bannerDao()
}
