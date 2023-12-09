package com.faizul.myquran.core.data.network.adzan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faizul.myquran.core.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JadwalAdzanRepository {
    private val _jadwalAdzan = MutableLiveData<JadwalAdzanResponse>()
    val jadwalAdzan: LiveData<JadwalAdzanResponse> = _jadwalAdzan

    fun getJadwalAdzan(year: Int, month: Int, latitude: Double, longitude: Double): LiveData<JadwalAdzanResponse> {
        // Assuming you have the necessary parameters here
        ApiConfig.getAdzanService.getCalendar(year, month, latitude, longitude).enqueue(object :
            Callback<JadwalAdzanResponse> {
            override fun onResponse(call: Call<JadwalAdzanResponse>, response: Response<JadwalAdzanResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _jadwalAdzan.postValue(response.body())
                    }
                } else {
                    Log.e("Repository", "onResponse: Call error with HTTP status code " + response.code())
                }
            }

            override fun onFailure(call: Call<JadwalAdzanResponse>, t: Throwable) {
                Log.e("Repository", "onFailure: " + t.localizedMessage)
            }
        })

        return jadwalAdzan
    }
}