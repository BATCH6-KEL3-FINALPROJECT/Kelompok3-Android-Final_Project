package com.project.skypass.presentation.home

import androidx.lifecycle.ViewModel
import com.project.skypass.data.repository.favoritedestination.FavoriteDestinationRepository

class HomeViewModel(private val repository: FavoriteDestinationRepository): ViewModel() {
    fun getFavoriteDestination() = repository.getFavoriteDestination()
}