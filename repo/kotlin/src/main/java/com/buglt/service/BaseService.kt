package com.buglt.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://script.google.com/"
const val ENDPOINT = "macros/s/AKfycbxcNz-wPv-A82j4ZXSk5w2zRCg6mV6BPN54dRQWOrfe3xrMB31ezdfopg39gk6xJ6UE/exec?"
const val CREATE_ACTION = "create"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object BugltApi {
    val retrofitService: BugltApiService by lazy {
        retrofit.create(BugltApiService::class.java)
    }
}