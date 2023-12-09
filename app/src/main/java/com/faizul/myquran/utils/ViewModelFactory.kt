package com.faizul.myquran.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faizul.myquran.core.data.network.adzan.JadwalAdzanRepository
import com.faizul.myquran.core.data.network.adzan.JadwalAdzanViewModel

class ViewModelFactory(private val repository: JadwalAdzanRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JadwalAdzanViewModel::class.java)) {
            return JadwalAdzanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}