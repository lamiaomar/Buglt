package com.buglt.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://script.google.com/"
const val ENDPOINT = "macros/s/AKfycbwbR7rSLeHB9R1SgYxIfOoWfyKpDHDlC6DUznVLtLqKH79xCB6QtftDeVK0jyt2uyAd/exec?"

// Actions for the BugltApi
const val CREATE_ACTION = "create"
const val CREATE_GET_TICKET = "getTickets"

// Moshi instance for JSON serialization and deserialization
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit instance for handling HTTP requests
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Singleton object representing the BugltApi.
 * It provides an instance of [BugltApiService] for making API calls.
 */
object BugltApi {
    val retrofitService: BugltApiService by lazy {
        retrofit.create(BugltApiService::class.java)
    }
}