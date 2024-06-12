package com.project.skypass.data.repository.favoritedestination

import com.project.skypass.data.datasource.home.favdestination.FavoriteDestinationDataSource
import com.project.skypass.data.model.Destination

class FavoriteDestinationRepositoryImpl(private val dataSource: FavoriteDestinationDataSource): FavoriteDestinationRepository {
    override fun getFavoriteDestination(): List<Destination> {
        return dataSource.getFavoriteDestinationData()
    }
}