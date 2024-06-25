package com.project.skypass.data.datasource.home.banner

import com.project.skypass.data.model.BannerHome

class BannerDummyDataSourceImpl : BannerDummyDataSource{
    override fun getBannerData(): List<BannerHome> {
        return listOf(
            BannerHome(
                title = "Bali - Island Paradise",
                city = "Bali",
                imageUrl = "https://i.pinimg.com/originals/57/ca/40/57ca408e09981a06f446b0617468c4c8.jpg",
                description = "Discover the enchanting beauty of Bali.",
                longDescription = "Bali, the Island of the Gods, is a mesmerizing destination with lush landscapes, pristine beaches, and a rich cultural heritage. Explore ancient temples, witness traditional ceremonies, and immerse yourself in the vibrant arts scene.",
                linkInfo = "https://www.example.com/bali"
            ),
            BannerHome(
                title = "Makassar - Gateway to Eastern Indonesia",
                city = "Makassar",
                imageUrl = "https://i.pinimg.com/originals/8a/50/22/8a502259509e3f2a65cd5f01912f80b1.jpg",
                description = "Experience the vibrant culture of Makassar.",
                longDescription = "Makassar, a bustling port city in South Sulawesi, is a melting pot of cultures and traditions. Explore historical landmarks, indulge in delicious cuisine, and discover the natural beauty of the surrounding islands.",
                linkInfo = "https://www.example.com/makassar"
            ),
            BannerHome(
                title = "Selangor - Heart of Malaysia",
                city = "Selangor",
                imageUrl = "https://i.pinimg.com/564x/ac/04/e9/ac04e9fbc3f0b748254e9a2708b6b684.jpg",
                description = "Explore the vibrant state of Selangor.",longDescription = "Selangor, a dynamic state in Malaysia, offers a mix of urban excitement and natural wonders. Discover bustling cities, explore charming towns, and immerse yourself in the rich cultural heritage.",
                linkInfo = "https://www.example.com/selangor"
            ),
            BannerHome(
                title = "Beijing - Ancient Capital of China",
                city = "Beijing",
                imageUrl = "https://i.pinimg.com/564x/a5/ca/e7/a5cae74646b157820437a56b574d3cae.jpg",
                description = "Journey through the historical wonders of Beijing.",
                longDescription = "Beijing, the capital of China, is a city steeped in history and culture. Explore iconic landmarks like the Forbidden City and the Great Wall, and immerse yourself in the vibrant arts and culinary scene.",
                linkInfo = "https://www.example.com/beijing"
            ),    BannerHome(
                title = "Singapore - A Fusion of Cultures",
                city = "Singapore",imageUrl = "https://i.pinimg.com/564x/de/db/8a/dedb8a168af58b7a3c8f64a66b9ba9f7.jpg",
                description = "Experience the dynamic city-state of Singapore.",
                longDescription = "Singapore is a vibrant melting pot of cultures, offering a unique blend of modern architecture, lush gardens, and culinary delights. Explore iconic landmarks, indulge in world-class shopping, and immerse yourself in the city's diverse heritage.",
                linkInfo = "https://www.example.com/singapore"
            ),
            BannerHome(
                title = "Manila - Pearl of the Orient",
                city = "Manila",
                imageUrl = "https://i.pinimg.com/564x/5d/37/ff/5d37ff613951e54fcf9e8c962178004f.jpg",
                description = "Discover the historical charm of Manila.",
                longDescription = "Manila, the capital of the Philippines, is a city rich in history and culture. Explore centuries-old churches, wander through bustling markets, and experience the vibrant nightlife.",
                linkInfo = "https://www.example.com/manila"
            )
        )
    }

}