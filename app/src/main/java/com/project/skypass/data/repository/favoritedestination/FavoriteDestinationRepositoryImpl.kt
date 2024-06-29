package com.project.skypass.data.repository.favoritedestination

import com.project.skypass.data.datasource.home.favdestination.FavoriteDestinationDataSource
import com.project.skypass.data.mapper.toDestinationFavorite
import com.project.skypass.data.model.Destination
import com.project.skypass.utils.ResultWrapper
import com.project.skypass.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class FavoriteDestinationRepositoryImpl(private val dataSource: FavoriteDestinationDataSource) : FavoriteDestinationRepository {
    /*override fun getFavoriteDestination(): List<Destination> {
        return dataSource.getFavoriteDestinationData()
    }*/
    override fun getFavoriteDestination(): Flow<ResultWrapper<List<Destination>>> {
        return proceedFlow {
            dataSource.getFavoriteDestinationData().data.toDestinationFavorite()
        }
    }
}
