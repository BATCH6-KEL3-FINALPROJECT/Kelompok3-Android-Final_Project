package com.project.skypass.presentation.history.filter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.search.SearchRepository
import kotlinx.coroutines.Dispatchers

class SearchHistoryViewModel(
    private val repository: SearchRepository,
    private val prefRepository: PrefRepository,
    private val historyRepository: HistoryRepository,
) : ViewModel() {
    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun search(query: String? = null) = repository.getSearchResults(query).asLiveData(Dispatchers.IO)

    fun getBookingHistory(
        token: String,
        search: String?,
        date: String?,
        until: String?,
    ) = search?.let {
        historyRepository.getBookingHistory(token, it, null, null).asLiveData(Dispatchers.IO)
    }
}
