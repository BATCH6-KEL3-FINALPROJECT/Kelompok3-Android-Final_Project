package com.project.skypass.data.datasource.home.favdestination

import com.project.skypass.data.model.Destination
import com.project.skypass.data.source.network.model.destinationfavorite.DestinationFavoriteResponse
import com.project.skypass.data.source.network.service.ApiService

class FavoriteDestinationDataSourceImpl(private val service: ApiService): FavoriteDestinationDataSource {

    override suspend fun getFavoriteDestinationData(): DestinationFavoriteResponse {
        return service.getDestinationFavorite()
    }

    /*override fun getFavoriteDestinationData(): List<Destination> {
        return listOf(
            Destination(
                isRoundTrip = true,
                from = "Jakarta",
                to = "Bali",
                departureDate = "11-06-2024",
                returnDate = "12-06-2024",
                passengers = 1,
                seatClass = "economy",
                discount = "Holiday Special",
                price = 1000000.0,
                imageUrl = "https://i.pinimg.com/originals/57/ca/40/57ca408e09981a06f446b0617468c4c8.jpg",
                airline = "AirAsia"
            ),
            Destination(
                isRoundTrip = true,
                from = "Bali",
                to = "Makassar",
                departureDate = "6-06-2024",
                returnDate = "11-06-2024",
                passengers = 1,
                seatClass = "economy",
                discount = "Early Bird",
                price = 900000.0,
                imageUrl = "https://i.pinimg.com/originals/8a/50/22/8a502259509e3f2a65cd5f01912f80b1.jpg",
                airline = "AirAsia"
            ),
            Destination(
                isRoundTrip = true,
                from = "Singapore",
                to = "Selangor",
                departureDate = "5-06-2024",
                returnDate = "8-06-2024",
                passengers = 2,
                seatClass = "business",
                discount = "Early Bird",
                price = 10000000.0,
                imageUrl = "https://i.pinimg.com/564x/ac/04/e9/ac04e9fbc3f0b748254e9a2708b6b684.jpg",
                airline = "Singapore Airlines"
            ),
            Destination(
                isRoundTrip = true,
                from = "Tokyo",
                to = "Beijing",
                departureDate = "7-06-2024",
                returnDate = "12-06-2024", // One-way trip
                passengers = 1,
                seatClass = "premium economy",
                discount = "Early Bird",
                price = 1800000.0,
                imageUrl = "https://i.pinimg.com/564x/a5/ca/e7/a5cae74646b157820437a56b574d3cae.jpg",
                airline = "Japan Airlines"
            ),
            Destination(
                isRoundTrip = true,
                from = "Jakarta",
                to = "Singapore",
                departureDate = "12-06-2024",
                returnDate = "13-06-2024",
                passengers = 3,
                seatClass = "economy",
                discount = "Family Package",
                price = 1850000.0,
                imageUrl = "https://i.pinimg.com/564x/de/db/8a/dedb8a168af58b7a3c8f64a66b9ba9f7.jpg",
                airline = "Qatar Airways"
            ),
            Destination(
                isRoundTrip = true,
                from = "Makassar",
                to = "Bali",
                departureDate = "9-06-2024",
                returnDate = "11-06-2024",
                passengers = 2,
                seatClass = "economy",
                discount = "Weekend Getaway",
                price = 2000000.0,
                imageUrl = "https://i.pinimg.com/564x/02/21/ad/0221ad079845c3157be2a3e2bef978ce.jpg", // Replace with actual image URL
                airline = "AirAsia"
            ),
            Destination(
                isRoundTrip = true,
                from = "Singapore",
                to = "Manila",
                departureDate = "5-06-2024",
                returnDate = "8-06-2024",
                passengers = 1,
                seatClass = "business",
                discount = "Early Bird",
                price = 12350000.0,
                imageUrl = "https://i.pinimg.com/564x/5d/37/ff/5d37ff613951e54fcf9e8c962178004f.jpg", // Replace with actual image URL
                airline = "Singapore Airlines"
            ),
            Destination(
                isRoundTrip = true,
                from = "Jakarta",
                to = "Lisbon",
                departureDate = "6-06-2024",
                returnDate ="9-06-2024" , // One-way trip
                passengers = 4,
                seatClass = "economy",
                discount = "Family Package",
                price = 2350000.0,
                imageUrl = "https://i.pinimg.com/564x/0e/e2/95/0ee295d8c6adbed7d133db2b5dcc2b45.jpg", // Replace with actual image URL
                airline = "Thai Airways"
            ),

            // Indonesian Cities
            Destination(
                isRoundTrip = true,
                from = "Bali",
                to = "Basel",
                departureDate = "9-06-2024",
                returnDate = "11-06-2024",
                passengers = 2,
                seatClass = "first class",
                discount = "Weekend Getaway",
                price = 26000000.0,
                imageUrl = "https://i.pinimg.com/564x/59/d6/bd/59d6bd74cb9072f7987084fa6c31b71b.jpg", // Replace with actual image URL
                airline = "Garuda Indonesia"
            )
        )
    }*/
}