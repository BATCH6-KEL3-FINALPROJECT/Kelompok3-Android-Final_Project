package com.project.skypass.data.datasource.home.banner

import com.project.skypass.data.model.BannerHome

interface BannerDummyDataSource {
    fun getBannerData(): List<BannerHome>
}
