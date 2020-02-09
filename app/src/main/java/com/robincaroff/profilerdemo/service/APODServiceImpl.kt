package com.robincaroff.profilerdemo.service

import com.robincaroff.profilerdemo.model.APOD
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object APODServiceImpl : APODService {

    private val client = OkHttpClient().newBuilder()
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: APODRetrofitService =
        retrofit.create<APODRetrofitService>(
            APODRetrofitService::class.java
        )

    override suspend fun getAPODs(): List<APOD> {
        val response = service.getAPODs(startDate = "2020-01-01", endDate = "2020-02-07")
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body.filter { apod -> apod.media_type == "image" }
            }
        }
        return emptyList()
    }
}