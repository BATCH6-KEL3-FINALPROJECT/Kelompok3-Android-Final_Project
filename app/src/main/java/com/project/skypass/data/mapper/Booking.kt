package com.project.skypass.data.mapper

import com.project.skypass.data.model.Booking
import com.project.skypass.data.source.network.model.booking.GetBookingDataItemResponse

fun GetBookingDataItemResponse?.getBookingData() =
        Booking(
            bookingId = this?.bookingId.orEmpty(),
            bookingCode = this?.bookingCode.orEmpty(),
            userId = this?.userId.orEmpty(),
            flightId = this?.flightId.orEmpty(),
            paymentId = this?.paymentId.orEmpty(),
            bookingDate = this?.bookingDate.orEmpty(),
            isRoundTrip = this?.isRoundTrip ?: false,
            noOfTicket = this?.noOfTicket ?: 0,
            status = this?.status.orEmpty(),
            totalPrice = this?.totalPrice.orEmpty(),
            createdAt = this?.createdAt.orEmpty(),
            updatedAt = this?.updatedAt.orEmpty(),
            adultPrice = this?.adultPrice ?: 0,
            seatClass = this?.seatClass.orEmpty(),
            totalAdult = this?.totalAdult ?: 0,
            totalChild = this?.totalChild ?: 0,
            totalBaby = this?.totalBaby ?: 0,
            airlineId = this?.flightData?.airlineId.orEmpty(),
            flightDuration = this?.flightData?.flightDuration ?: 0,
            flightCode = this?.flightData?.flightCode.orEmpty(),
            isPromo = this?.flightData?.isPromo ?: false,
            departureDate = this?.flightData?.departureDate.orEmpty(),
            departureTime = this?.flightData?.departureTime.orEmpty(),
            arrivalDate = this?.flightData?.arrivalDate.orEmpty(),
            arrivalTime = this?.flightData?.arrivalTime.orEmpty(),
            departureAirport = this?.flightData?.departureAirport.orEmpty(),
            arrivalAirport = this?.flightData?.arrivalAirport.orEmpty(),
            departureCity = this?.flightData?.departingAirport?.city.orEmpty(),
            arrivalCity = this?.flightData?.arrivingAirport?.city.orEmpty(),
            departureAirportId = this?.flightData?.departureAirportId.orEmpty(),
            arrivalAirportId = this?.flightData?.arrivalAirportId.orEmpty(),
    )

fun Collection<GetBookingDataItemResponse>?.getListBookingData() = this?.map { it.getBookingData() } ?: listOf()
