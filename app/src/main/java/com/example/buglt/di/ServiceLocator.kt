package com.example.buglt.di

import com.buglt.repo.datasource.BaseRemoteDataSource
import com.buglt.repo.domin.repository.AppRepositoryImpl
import com.buglt.repo.service.BugltApi
import com.buglt.repo.service.BugltApiService

// TODO Remove and use Hilt dependency injection
object ServiceLocator {
    private fun provideBugltApi(): BugltApiService = BugltApi.retrofitService

    private fun provideBaseRemoteDataSource(): BaseRemoteDataSource = BaseRemoteDataSource(
        provideBugltApi()
    )

    fun provideAppRepository(): AppRepositoryImpl = AppRepositoryImpl(
        provideBaseRemoteDataSource()
    )

}