package com.project.skypass.data.repository.bannerHome

import com.project.skypass.data.model.BannerHome

interface BannerHomeRepository {
    fun getBannerData(): List<BannerHome>
}
