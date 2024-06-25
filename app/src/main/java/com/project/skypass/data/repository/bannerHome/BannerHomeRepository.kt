package com.project.skypass.data.repository.bannerHome

import com.project.skypass.data.model.BannerHome
import com.project.skypass.data.model.Destination

interface BannerHomeRepository {
    fun getBannerData(): List<BannerHome>
}
