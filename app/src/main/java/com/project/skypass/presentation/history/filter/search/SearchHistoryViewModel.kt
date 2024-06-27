package com.project.skypass.presentation.history.filter.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.History
import com.project.skypass.data.model.SearchHistoryHome
import com.project.skypass.data.repository.history.HistoryRepository
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.search.SearchRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class SearchHistoryViewModel(
    private val repository: SearchRepository,
    private val prefRepository: PrefRepository,
    private val historyRepository: HistoryRepository
): ViewModel() {
    fun getToken(): String {
        return prefRepository.getToken()
    }
    fun search(query: String? = null) = repository.getSearchResults(query).asLiveData(Dispatchers.IO)
    fun getBookingHistory(token: String, search: String?) =
        search?.let { historyRepository.getBookingHistory(token, it).asLiveData(Dispatchers.IO) }
    fun insertHistorySearch(token: String, history: String): LiveData<ResultWrapper<Boolean>> {
        return repository.createHistorySearchHome(token, history).asLiveData(Dispatchers.IO)
    }
}