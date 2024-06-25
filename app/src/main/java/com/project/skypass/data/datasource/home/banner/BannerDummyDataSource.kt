package com.project.skypass.data.datasource.home.banner

import com.project.skypass.data.model.BannerHome
import com.project.skypass.data.model.Destination

interface BannerDummyDataSource {
    fun getBannerData(): List<BannerHome>
}