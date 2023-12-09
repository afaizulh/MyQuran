package com.faizul.myquran.core.data.network.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class JadwalAdzanViewModel(private val repository: JadwalAdzanRepository) : ViewModel() {
    val jadwalAdzan: LiveData<JadwalAdzanResponse> = repository.jadwalAdzan

    fun getJadwalAdzan(year: Int, month: Int, latitude: Double, longitude: Double) {
        repository.getJadwalAdzan(year, month, latitude, longitude)
    }
}