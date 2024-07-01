package com.project.skypass.data.repository.bannerHome

import com.project.skypass.data.datasource.home.banner.BannerDummyDataSource
import com.project.skypass.data.model.BannerHome

class BannerHomeRepositoryImpl(private val dataSource: BannerDummyDataSource) : BannerHomeRepository {
    override fun getBannerData(): List<BannerHome> {
        return dataSource.getBannerData()
    }
}
