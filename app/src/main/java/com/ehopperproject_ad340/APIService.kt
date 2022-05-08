package com.ehopperproject_ad340

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://web6.seattle.gov/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CamApiService {
    @GET("Travelers/api/Map/Data?zoomId=13&type=2")
    fun getProperties(): Call<CallResponse>
}

object CamApi {
    val retrofitService : CamApiService by lazy {
        retrofit.create(CamApiService::class.java)
    }
}



