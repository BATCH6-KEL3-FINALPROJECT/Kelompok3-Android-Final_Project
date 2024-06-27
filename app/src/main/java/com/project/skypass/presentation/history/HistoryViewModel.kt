package com.project.skypass.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import kotlinx.coroutines.Dispatchers

class HistoryViewModel(
    private val prefRepository: PrefRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getAllHistory(token: String) =
        historyRepository.getHistory(token).asLiveData(Dispatchers.IO)

    fun getBookingHistory(token: String, search: String?, date: String?, until: String?) =
        search?.let { historyRepository.getBookingHistory(token, it, null, null).asLiveData(Dispatchers.IO) }
}