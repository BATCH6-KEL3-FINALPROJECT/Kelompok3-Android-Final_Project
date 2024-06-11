package com.project.skypass.data.datasource.home.favdestination

import com.project.skypass.data.model.Destination

interface FavoriteDestinationDataSource {
    fun getFavoriteDestinationData(): List<Destination>
}