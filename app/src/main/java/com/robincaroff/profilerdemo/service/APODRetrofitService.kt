package com.robincaroff.profilerdemo.service

import com.robincaroff.profilerdemo.model.APOD
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APODRetrofitService {

    /**
     * Get APODs
     *
     * Get APODs
     *
     * @return The countries list
     */
    @GET("planetary/apod")
    suspend fun getAPODs(
        @Query("api_key") apiKey: String = "DEMO_KEY", @Query("start_date") startDate: String, @Query(
            "end_date"
        ) endDate: String
    ): Response<List<APOD>>
}