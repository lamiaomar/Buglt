package com.buglt.repo.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://script.google.com/"
const val ENDPOINT = "macros/s/AKfycbx9Hun2YXKyozt9FKS9uWO8EyK60PDulHZa0SvRVbfayYqADXEVquVFixIztDszNob7/exec?"
const val CREATE_ACTION = "create"

//"https://script.google.com/"

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