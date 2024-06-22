package com.project.skypass.presentation.checkout.checkoutDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.CheckoutPayment
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.PassengersData
import com.project.skypass.databinding.ActivityCheckoutDetailBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.utils.toIndonesianFormat

class CheckoutDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDetailBinding.inflate(layoutInflater) }

    var priceTotal: Int? = null
    var priceAdult: Int? = null
    var priceChild: Int? = null
    var priceBaby: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeResult() {
//        observe view model
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?.let {

            intent.extras?.getParcelable<OrderPassengers>(CheckoutDataPassengerActivity.EXTRA_USER_ORDER)
                ?.let { orderPassenger ->
                    sendOrderData(it,orderPassenger)
                    setProfileData(it, orderPassenger)
                    val toCheckout = sendToPayment(it, orderPassenger)
                    Log.d("checkSeatInPassengersData", "Passengers Data: ${toCheckout.passengersData}")
                }

        }
    }
    private fun sendToPayment(item: OrderUser, passengerData: OrderPassengers):CheckoutPayment {
        val totalPassengers = item.passengersBaby!! + item.passengersAdult!! + item.passengersChild!!
        val payment = CheckoutPayment(
            totalAmount = priceTotal!!,
            departureFlightId = item.flightId!!,
            returnFlightId = item.flightIdRoundTrip!!,
            passengersData = passengerData.passengers!!.mapIndexed {index, it ->
                PassengersData(
                    title = it.title,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    dateOfBirth = it.dateOfBirth,
                    email = it.email,
                    phoneNumber = it.phoneNumber,
                    nationality = it.nationality,
                    passportNo = it.passportNo,
                    issuingCountry = it.issuingCountry,
                    validUntil = it.validUntil,
                    seatsDepartureId = passengerData.seatIdDeparture?.get(index),
                    seatsArrivalId = passengerData.seatIdArrival?.get(index),
                    passengerType = null
                )
            },
            noOfPassenger = totalPassengers
        )
        return payment
    }

    private fun setProfileData(item: OrderUser, passengerData: OrderPassengers) {
        binding.apply {
            if (item.isRoundTrip == false && item.supportRoundTrip == false && item.seatsAvailableRoundTrip != null) {
                tvTotalPrice.text =
                    (item.priceTotal?.plus(item.priceTotalRoundTrip!!)).toIndonesianFormat()
                rvTicketDetail.tvAirportDeparture.text = item.departureAirportName
                rvTicketDetail.tvAirportArrival.text = item.arrivalAirportName
                rvTicketDetail.tvAirline.text = item.airlineName
                rvTicketDetail.tvCityDeparture.text = item.arrivalCity
                rvTicketDetail.tvCityArrival.text = item.departureCity
                rvTicketDetail.tvDateDeparture.text = item.flightDepartureDate
                rvTicketDetail.tvDateArrival.text = item.flightArrivalDate
                rvTicketDetail.tvTimeDeparture.text = item.departureTime
                rvTicketDetail.tvTimeArrival.text = item.arrivalTime
                rvTicketDetail.tvInfoDetail.text = item.flightDescription
                rvTicketDetail.tvFlightCode.text = item.flightCode
                rvTicketDetail.tvSeatChose.text = passengerData.seatOrderDeparture.toString()

                rvTicketDetailRound.tvAirportDeparture.text = item.departureAirportNameRoundTrip
                rvTicketDetailRound.tvAirportArrival.text = item.arrivalAirportNameRoundTrip
                rvTicketDetailRound.tvAirline.text = item.airlineNameRoundTrip
                rvTicketDetailRound.tvCityDeparture.text = item.departureCity
                rvTicketDetailRound.tvCityArrival.text = item.arrivalCity
                rvTicketDetailRound.tvDateDeparture.text = item.flightDepartureDateRoundTrip
                rvTicketDetailRound.tvDateArrival.text = item.flightArrivalDateRoundTrip
                rvTicketDetailRound.tvTimeDeparture.text = item.departureTimeRoundTrip
                rvTicketDetailRound.tvTimeArrival.text = item.arrivalTimeRoundTrip
                rvTicketDetailRound.tvInfoDetail.text = item.flightDescriptionRoundTrip
                rvTicketDetailRound.tvFlightCode.text = item.flightCodeRoundTrip
                rvTicketDetailRound.tvSeatChose.text = passengerData.seatOrderArrival.toString()
                rvTicketDetailRound.root.isVisible = true
                btnSubmit.text = getString(R.string.text_button_detail_checkout)

            } else {
                rvTicketDetailRound.root.isVisible = false
                tvTotalPrice.text = item.priceTotal.toIndonesianFormat()
                rvTicketDetail.tvAirportDeparture.text = item.departureAirportName
                rvTicketDetail.tvAirportArrival.text = item.arrivalAirportName
                rvTicketDetail.tvAirline.text = item.airlineName
                rvTicketDetail.tvCityDeparture.text = item.departureCity
                rvTicketDetail.tvCityArrival.text = item.arrivalCity
                rvTicketDetail.tvDateDeparture.text = item.flightDepartureDate
                rvTicketDetail.tvDateArrival.text = item.flightArrivalDate
                rvTicketDetail.tvTimeDeparture.text = item.departureTime
                rvTicketDetail.tvTimeArrival.text = item.arrivalTime
                rvTicketDetail.tvInfoDetail.text = item.flightDescription
                rvTicketDetail.tvFlightCode.text = item.flightCode
                rvTicketDetail.tvSeatChose.text = passengerData.seatOrderDeparture.toString()
                if (item.seatsAvailableRoundTrip != null){
                    btnSubmit.text = getString(R.string.btn_detail_checkoutBack)
                }else{
                    btnSubmit.text = getString(R.string.text_button_detail_checkout)
                }

            }
            //set price detail
            tvAdultCount.text = item.passengersAdult.toString()
            tvChildCount.text = item.passengersChild.toString()
            tvBabyCount.text = item.passengersBaby.toString()

            priceAdult = item.priceTotal?.times(item.passengersAdult!!)
                ?.plus(item.priceTotalRoundTrip?.times(item.passengersAdult!!)!!)
            priceChild = item.priceTotal?.times(item.passengersChild!!)
                ?.plus(item.priceTotalRoundTrip?.times(item.passengersChild!!)!!)
            priceBaby = (item.priceTotal!! / 2).times(item.passengersBaby!!)
                .plus((item.priceTotalRoundTrip!! / 2).times(item.passengersBaby!!))
            priceTotal = priceAdult!! + priceChild!! + priceBaby!!
            tvPriceAdult.text = "${priceAdult.toIndonesianFormat()}"
            tvPricechild.text = "${priceChild.toIndonesianFormat()}"
            tvPriceBaby.text = "${priceBaby.toIndonesianFormat()}"
            tvTotalPrice.text = "${priceTotal.toIndonesianFormat()}"
            item.paymentPrice = priceTotal
        }
    }

    private fun sendOrderData(item: OrderUser, passengerData: OrderPassengers) {

        binding.btnSubmit.setOnClickListener {
            if (item.supportRoundTrip == false) {
                MainActivity.sendDataOrder(
                    this,
                    item,
                )
            } else {

                CheckoutSeatActivity.sendDataOrder(
                    this,
                    OrderUser(
                        // Home data
                        id = item.id,
                        arrivalCity = item.arrivalCity,
                        arrivalDate = item.arrivalDate,
                        seatClass = item.seatClass,
                        departureCity = item.departureCity,
                        departureDate = item.departureDate,
                        passengersTotal = item.passengersTotal,
                        passengersAdult = item.passengersAdult,
                        passengersBaby = item.passengersBaby,
                        passengersChild = item.passengersChild,
                        isRoundTrip = item.isRoundTrip,
                        supportRoundTrip = false,
                        orderDate = item.orderDate,

                        // Flight data (One Way)
                        airlineCode = item.airlineCode,
                        airlineName = item.airlineName,
                        arrivalAirportName = item.arrivalAirportName,
                        arrivalIATACode = item.arrivalIATACode,
                        arrivalTime = item.arrivalTime,
                        departureAirportName = item.departureAirportName,
                        departureIATACode = item.departureIATACode,
                        departureTime = item.departureTime,
                        flightCode = item.flightCode,
                        flightDescription = item.flightDescription,
                        flightDuration = item.flightDuration,
                        flightDurationFormat = item.flightDurationFormat,
                        flightId = item.flightId,
                        flightStatus = item.flightStatus,
                        flightSeat = item.seatClass,
                        flightArrivalDate = item.flightArrivalDate,
                        flightDepartureDate = item.flightDepartureDate,
                        planeType = item.planeType,
                        priceAdult = item.priceAdult,
                        priceBaby = item.priceBaby,
                        priceChild = item.priceChild,
                        priceTotal = item.priceTotal,
                        paymentPrice = item.paymentPrice,
                        seatsAvailable = item.seatsAvailable,
                        terminal = item.terminal,

                        // Flight data (Round Trip)
                        airlineCodeRoundTrip = item.airlineCodeRoundTrip,
                        airlineNameRoundTrip = item.airlineNameRoundTrip,
                        arrivalAirportNameRoundTrip = item.arrivalAirportNameRoundTrip,
                        arrivalIATACodeRoundTrip = item.arrivalIATACodeRoundTrip,
                        arrivalTimeRoundTrip = item.arrivalTimeRoundTrip,
                        departureAirportNameRoundTrip = item.departureAirportNameRoundTrip,
                        departureIATACodeRoundTrip = item.departureIATACodeRoundTrip,
                        departureTimeRoundTrip = item.departureTimeRoundTrip,
                        flightCodeRoundTrip = item.flightCodeRoundTrip,
                        flightDescriptionRoundTrip = item.flightDescriptionRoundTrip,
                        flightDurationRoundTrip = item.flightDurationRoundTrip,
                        flightDurationFormatRoundTrip = item.flightDurationFormatRoundTrip,
                        flightIdRoundTrip = item.flightIdRoundTrip,
                        flightStatusRoundTrip = item.flightStatusRoundTrip,
                        flightSeatRoundTrip = item.flightSeatRoundTrip,
                        flightArrivalDateRoundTrip = item.flightArrivalDateRoundTrip,
                        flightDepartureDateRoundTrip = item.flightDepartureDateRoundTrip,
                        planeTypeRoundTrip = item.planeTypeRoundTrip,
                        priceAdultRoundTrip = item.priceAdultRoundTrip,
                        priceBabyRoundTrip = item.priceBabyRoundTrip,
                        priceChildRoundTrip = item.priceChildRoundTrip,
                        priceTotalRoundTrip = item.priceTotalRoundTrip,
                        paymentPriceRoundTrip = item.paymentPriceRoundTrip,
                        seatsAvailableRoundTrip = item.seatsAvailableRoundTrip,
                        terminalRoundTrip = item.terminalRoundTrip
                    ),
                    passengerData
                )
            }
        }
    }

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        const val EXTRA_USER_ORDER = "EXTRA_USER_ORDER"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,
            orderPassenger: OrderPassengers
        ) {
            val intent = Intent(context, CheckoutDetailActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            intent.putExtra(EXTRA_USER_ORDER, orderPassenger)
            context.startActivity(intent)
        }
    }
}