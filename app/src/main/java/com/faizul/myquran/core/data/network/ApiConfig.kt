package com.faizul.myquran.core.data.network

import com.faizul.myquran.core.data.network.adzan.AdzanApiService
import com.faizul.myquran.core.data.network.quran.QuranApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {

    private inline fun <reified T> createService(baseUrl: String): T {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(T::class.java)
    }
    val getQuranService = createService<QuranApiService>("https://api.alquran.cloud/v1/")
    val getAdzanService = createService<AdzanApiService>("https://api.aladhan.com/v1/")
}