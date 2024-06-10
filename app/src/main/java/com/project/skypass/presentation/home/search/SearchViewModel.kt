package com.project.skypass.presentation.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.skypass.data.repository.search.SearchRepository
import kotlinx.coroutines.Dispatchers

class SearchViewModel(private val repository: SearchRepository): ViewModel() {
    fun search(query: String? = null) = repository.getSearchResults(query).asLiveData(Dispatchers.IO)
}