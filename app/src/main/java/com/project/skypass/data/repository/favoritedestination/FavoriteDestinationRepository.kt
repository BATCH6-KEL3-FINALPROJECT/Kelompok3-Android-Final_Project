package com.project.skypass.data.repository.favoritedestination

import com.project.skypass.data.model.Destination

interface FavoriteDestinationRepository {
    fun getFavoriteDestination(): List<Destination>
}