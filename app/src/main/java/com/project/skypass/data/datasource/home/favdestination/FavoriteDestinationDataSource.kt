package com.project.skypass.data.datasource.home.favdestination

import com.project.skypass.data.source.network.model.destinationfavorite.DestinationFavoriteResponse

interface FavoriteDestinationDataSource {
    suspend fun getFavoriteDestinationData(): DestinationFavoriteResponse
}
