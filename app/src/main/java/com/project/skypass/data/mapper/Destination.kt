package com.project.skypass.data.mapper

import com.project.skypass.data.model.Destination
import com.project.skypass.data.source.network.model.destinationfavorite.DestinationFavoriteItemResponse

fun DestinationFavoriteItemResponse?.toDestination() =
    Destination(
        airline = this?.airline.orEmpty(),
        continent = this?.continent.orEmpty(),
        departureDate = this?.departureDate.orEmpty(),
        discount = this?.discount.orEmpty(),
        from = this?.from.orEmpty(),
        imageUrl = this?.imageUrl.orEmpty(),
        isRoundTrip = this?.isRoundTrip ?: false,
        passengersAdult = this?.passengersAdult ?: 0,
        passengersBaby = this?.passengersBaby ?: 0,
        passengersChild = this?.passengersChild ?: 0,
        passengersTotal = this?.passengersTotal ?: 0,
        price = this?.price ?: 0,
        returnDate = this?.returnDate.orEmpty(),
        seatClass = this?.seatClass.orEmpty(),
        to = this?.to.orEmpty()
    )

fun Collection<DestinationFavoriteItemResponse>?.toDestinationFavorite() = this?.map { it.toDestination() } ?: listOf()