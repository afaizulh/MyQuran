package com.faizul.myquran.core.data.network.adzan

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AdzanApiService {
    @GET("calendar/{year}/{month}")
    fun getCalendar(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): Call<JadwalAdzanResponse>
}