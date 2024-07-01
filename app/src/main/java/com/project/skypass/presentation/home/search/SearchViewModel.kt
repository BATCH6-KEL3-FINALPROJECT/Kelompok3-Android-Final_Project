package com.project.skypass.presentation.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.model.SearchHistoryHome
import com.project.skypass.data.repository.pref.PrefRepository
import com.project.skypass.data.repository.search.SearchRepository
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class SearchViewModel(
    private val repository: SearchRepository,
    private val prefRepository: PrefRepository,
) : ViewModel() {
    fun getToken(): String {
        return prefRepository.getToken()
    }

    fun search(query: String? = null) = repository.getSearchResults(query).asLiveData(Dispatchers.IO)

    fun getHistorySearch(token: String): LiveData<ResultWrapper<List<SearchHistoryHome>>> {
        return repository.getAllHistorySearchHome(token).asLiveData(Dispatchers.IO)
    }

    fun insertHistorySearch(
        token: String,
        history: String,
    ): LiveData<ResultWrapper<Boolean>> {
        return repository.createHistorySearchHome(token, history).asLiveData(Dispatchers.IO)
    }

    fun deleteHistorySearch(
        token: String,
        id: Int,
    ): LiveData<ResultWrapper<Boolean>> {
        return repository.deleteHistorySearchHome(token, id).asLiveData(Dispatchers.IO)
    }
}
