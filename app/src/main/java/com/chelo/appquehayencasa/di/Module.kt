package com.chelo.appquehayencasa.di

import android.content.Context
import androidx.room.Room
import com.chelo.appquehayencasa.data.daos.CategoryDao
import com.chelo.appquehayencasa.data.daos.ProductDao
import com.chelo.appquehayencasa.data.daos.UserDao
import com.chelo.appquehayencasa.data.db.AppDb
import com.chelo.appquehayencasa.data.repository.ProductRepository
import com.chelo.appquehayencasa.notification.CheckExpireDateToNotifyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object Module {


    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): AppDb {
        return Room.databaseBuilder(
            context,
            AppDb::class.java,
            "db_app"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    fun providesUserDao(db: AppDb): UserDao {
        return db.userDao()
    }

    @Provides
    fun providesProductDao(db: AppDb): ProductDao {
        return db.productDao()
    }

    @Provides
    fun providesCategoryDao(db : AppDb) : CategoryDao{
        return db.categoryDao()
    }

    @Provides
    fun providesExpireUseCase(repo : ProductRepository) : CheckExpireDateToNotifyUseCase{
        return CheckExpireDateToNotifyUseCase(repo)
    }

}