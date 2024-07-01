package com.project.skypass.data.datasource.home.favdestination

import com.project.skypass.data.source.network.model.destinationfavorite.DestinationFavoriteResponse
import com.project.skypass.data.source.network.service.ApiService

class FavoriteDestinationDataSourceImpl(private val service: ApiService) : FavoriteDestinationDataSource {
    override suspend fun getFavoriteDestinationData(): DestinationFavoriteResponse {
        return service.getDestinationFavorite()
    }
}
