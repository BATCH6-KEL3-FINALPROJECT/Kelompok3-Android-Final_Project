package com.project.skypass.data.repository.favoritedestination

import com.project.skypass.data.model.Destination
import com.project.skypass.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FavoriteDestinationRepository {
    fun getFavoriteDestination(): Flow<ResultWrapper<List<Destination>>>
}
