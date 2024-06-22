package com.project.skypass.presentation.history.detailhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.History
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class DetailHistoryViewModel(
    private val prefRepository: PrefRepository,
    private val detailHistoryRepository: HistoryRepository
): ViewModel() {

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getDetailHistory(token: String, idDetail: String): LiveData<ResultWrapper<History>> {
        return detailHistoryRepository.getDetailHistory(token, idDetail).asLiveData(Dispatchers.IO)
    }
}