package com.example.buglt.di

import android.content.Context
import com.buglt.datasource.BaseRemoteDataSource
import com.buglt.service.BugltApi
import com.buglt.service.BugltApiService
import com.buglt.domin.repository.AppRepositoryImpl
import com.example.buglt.BugltApplication
import com.example.buglt.images.UploadScreenShotManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BugltApplication {
        return app as BugltApplication
    }

    @Provides
    @Singleton
    private fun provideBugltApiService(): BugltApiService = BugltApi.retrofitService

    @Provides
    @Singleton
    private fun provideRemoteDataSource(): BaseRemoteDataSource = BaseRemoteDataSource(
        provideBugltApiService()
    )

    @Provides
    @Singleton
    fun provideAppRepository(): AppRepositoryImpl = AppRepositoryImpl(provideRemoteDataSource())

    @Provides
    @Singleton
    fun provideUploadScreenShotManager(@ApplicationContext context: Context): UploadScreenShotManager {
        return UploadScreenShotManager()
    }
}