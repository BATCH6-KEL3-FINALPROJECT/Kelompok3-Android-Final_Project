package com.project.skypass.data.datasource.home.favdestination

import com.project.skypass.data.model.Destination

class FavoriteDestinationDataSourceImpl: FavoriteDestinationDataSource {
    override fun getFavoriteDestinationData(): List<Destination> {
        return listOf(
            Destination(
                isRoundTrip = true,
                from = "Jakarta",
                to = "Bali",
                departureDate = "2024-08-07",
                returnDate = "2024-08-10",
                passengers = "1",
                seatClass = "Economy",
                discount = "50%",
                price = 1000000.0,
                imageUrl = "https://assets.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/2023/05/07/WhatsApp-Image-2023-05-07-at-012334-1601479826.jpeg",
                airline = "AirAsia"
            ),
            Destination(
                isRoundTrip = true,
                from = "Bali",
                to = "Lombok",
                departureDate = "2024-08-08",
                returnDate = "2024-08-11",
                passengers = "1",
                seatClass = "Economy",
                discount = "50%",
                price = 2000000.0,
                imageUrl = "https://assets.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/2023/05/07/WhatsApp-Image-2023-05-07-at-012334-1601479826.jpeg",
                airline = "AirAsia"
            ),
            Destination(
                isRoundTrip = true,
                from = "Lombok",
                to = "Jakarta",
                departureDate = "2024-08-09",
                returnDate = "2024-08-12",
                passengers = "1",
                seatClass = "Economy",
                discount = "Limited",
                price = 3000000.0,
                imageUrl = "https://assets.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/2023/05/07/WhatsApp-Image-2023-05-07-at-012334-1601479826.jpeg",
                airline = "AirAsia"
            )
        )
    }
}