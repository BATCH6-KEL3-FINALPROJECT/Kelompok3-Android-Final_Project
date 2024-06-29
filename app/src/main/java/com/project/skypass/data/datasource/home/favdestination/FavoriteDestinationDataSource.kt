package com.project.skypass.data.datasource.home.favdestination

import com.project.skypass.data.source.network.model.destinationfavorite.DestinationFavoriteResponse

interface FavoriteDestinationDataSource {
    // fun getFavoriteDestinationData(): List<Destination>
    suspend fun getFavoriteDestinationData(): DestinationFavoriteResponse
}
