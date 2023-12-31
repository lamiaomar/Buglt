package com.example.buglt.di

import com.buglt.datasource.BaseRemoteDataSource
import com.buglt.domin.repository.AppRepositoryImpl
import com.buglt.service.BugltApi
import com.buglt.service.BugltApiService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

// TODO Remove and use Hilt dependency injection
object ServiceLocator {
    private fun provideBugltApi(): BugltApiService = BugltApi.retrofitService

    private fun provideBaseRemoteDataSource(): BaseRemoteDataSource = BaseRemoteDataSource(
        provideBugltApi()
    )

    private fun provideFirebaseInstance(): FirebaseFirestore = Firebase.firestore

    fun provideAppRepository(): AppRepositoryImpl = AppRepositoryImpl(
        provideBaseRemoteDataSource(),
        provideFirebaseInstance()
    )

}