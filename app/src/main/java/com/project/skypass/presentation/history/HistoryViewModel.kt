package com.project.skypass.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.History
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class HistoryViewModel(
    private val prefRepository: PrefRepository,
    private val historyRepository: HistoryRepository
): ViewModel() {

    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun getAllHistory(token: String) = historyRepository.getHistory(token).asLiveData(Dispatchers.IO)

}