package com.project.skypass.presentation.flight.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.R
import com.project.skypass.data.model.Flight
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityFlightResultBinding
import com.project.skypass.presentation.checkout.checkoutDataOrder.CheckoutDataOrdersActivity
import com.project.skypass.presentation.flight.detail.FlightDetailActivity
import com.project.skypass.utils.convertMinutesToHours
import com.project.skypass.utils.toIndonesianFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.time.times

class FlightResultActivity : AppCompatActivity() {
    private val binding: ActivityFlightResultBinding by lazy {
        ActivityFlightResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivBackDetail.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bindFlightData(flight: OrderUser) {
        flight.let { item ->

            binding.tvTotalPrice.text = item.priceTotal.toIndonesianFormat()
            binding.rvTicketDetail.tvAirportDeparture.text = item.departureAirportName
            binding.rvTicketDetail.tvAirportArrival.text = item.arrivalAirportName
            binding.rvTicketDetail.tvAirline.text = item.airlineName
            binding.rvTicketDetail.tvCityDeparture.text = item.departureCity
            binding.rvTicketDetail.tvCityArrival.text = item.arrivalCity
            binding.rvTicketDetail.tvDateDeparture.text = item.flightDepartureDate
            binding.rvTicketDetail.tvDateArrival.text = item.flightArrivalDate
            binding.rvTicketDetail.tvTimeDeparture.text = item.departureTime
            binding.rvTicketDetail.tvTimeArrival.text = item.arrivalTime
            binding.rvTicketDetail.tvInfoDetail.text = item.flightDescription
            binding.rvTicketDetail.tvFlightCode.text = item.flightCode


            if (item.isRoundTrip == false && item.supportRoundTrip == true) {
                binding.tvTotalPrice.text = (item.priceTotal?.plus(item.priceTotalRoundTrip!!)).toIndonesianFormat()
                binding.btnSelectFlight.text = "Lanjut Checkout"
                binding.tvTotal.text = "Total Harga"
                // change departure to arrival
                binding.rvTicketDetail.tvAirportDeparture.text = item.departureAirportNameRoundTrip
                binding.rvTicketDetail.tvAirportArrival.text = item.arrivalAirportNameRoundTrip
                binding.rvTicketDetail.tvAirline.text = item.airlineNameRoundTrip
                binding.rvTicketDetail.tvCityDeparture.text = item.arrivalCity
                binding.rvTicketDetail.tvCityArrival.text = item.departureCity
                binding.rvTicketDetail.tvDateDeparture.text = item.flightDepartureDateRoundTrip
                binding.rvTicketDetail.tvDateArrival.text = item.flightArrivalDateRoundTrip
                binding.rvTicketDetail.tvTimeDeparture.text = item.departureTimeRoundTrip
                binding.rvTicketDetail.tvTimeArrival.text = item.arrivalTimeRoundTrip
                binding.rvTicketDetail.tvInfoDetail.text = item.flightDescriptionRoundTrip
                binding.rvTicketDetail.tvFlightCode.text = item.flightCodeRoundTrip

                binding.tvTitleFlightResult.text = getString(R.string.text_detail_penerbangan)
                binding.rvTicketDetailRound.tvAirportDeparture.text = item.departureAirportName
                binding.rvTicketDetailRound.tvAirportArrival.text = item.arrivalAirportName
                binding.rvTicketDetailRound.tvAirline.text = item.airlineName
                binding.rvTicketDetailRound.tvCityDeparture.text = item.departureCity
                binding.rvTicketDetailRound.tvCityArrival.text = item.arrivalCity
                binding.rvTicketDetailRound.tvDateDeparture.text = item.flightDepartureDate
                binding.rvTicketDetailRound.tvDateArrival.text = item.flightArrivalDate
                binding.rvTicketDetailRound.tvTimeDeparture.text = item.departureTime
                binding.rvTicketDetailRound.tvTimeArrival.text = item.arrivalTime
                binding.rvTicketDetailRound.tvInfoDetail.text = item.flightDescription
                binding.rvTicketDetailRound.tvFlightCode.text = item.flightCode
                binding.rvTicketDetailRound.root.isVisible = true
            }else{
                binding.rvTicketDetailRound.root.isVisible = false
            }
        }
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?.let {
            sendOrderData(it)
            bindFlightData(it)
        }
    }

    private fun sendOrderData(item: OrderUser) {

            binding.btnSelectFlight.setOnClickListener {
                if (item.isRoundTrip == false) {
                    CheckoutDataOrdersActivity.sendDataOrder(
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
                            supportRoundTrip = item.supportRoundTrip,
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
                    )
                }else{
                    FlightDetailActivity.startActivity(
                        this,
                        OrderUser(
                            // Change departure into arrival only in date & city
                            id = item.id,
                            arrivalCity = item.departureCity,
                            arrivalDate = item.departureDate,
                            seatClass = item.seatClass,
                            departureCity = item.arrivalCity,
                            departureDate = item.arrivalDate,
                            passengersTotal = item.passengersTotal,
                            passengersAdult = item.passengersAdult,
                            passengersBaby = item.passengersBaby,
                            passengersChild = item.passengersChild,
                            // change is round trip into false
                            isRoundTrip = false,
                            supportRoundTrip = item.supportRoundTrip,
                            orderDate = item.orderDate,


                            // set data round trip
                            airlineCode = item.airlineCodeRoundTrip,
                            airlineName = item.airlineNameRoundTrip,
                            arrivalAirportName = item.arrivalAirportNameRoundTrip,
                            arrivalIATACode= item.arrivalIATACodeRoundTrip,
                            arrivalTime = item.arrivalTimeRoundTrip,
                            departureAirportName = item.departureAirportNameRoundTrip,
                            departureIATACode = item.departureIATACodeRoundTrip,
                            departureTime = item.departureTimeRoundTrip,
                            flightCode = item.flightCodeRoundTrip,
                            flightDescription = item.flightDescriptionRoundTrip,
                            flightDuration= item.flightDurationRoundTrip,
                            flightDurationFormat = item.flightDurationFormatRoundTrip,
                            flightId = item.flightIdRoundTrip,
                            flightStatus = item.flightStatusRoundTrip,
                            flightSeat = item.flightSeatRoundTrip,
                            flightArrivalDate = item.flightArrivalDateRoundTrip,
                            flightDepartureDate = item.flightDepartureDateRoundTrip,
                            planeType = item.planeTypeRoundTrip,
                            priceAdult = item.priceAdultRoundTrip,
                            priceBaby = item.priceBabyRoundTrip,
                            priceChild = item.priceChildRoundTrip,
                            priceTotal = item.priceTotalRoundTrip,
                            paymentPrice = item.paymentPriceRoundTrip,
                            seatsAvailable = item.seatsAvailableRoundTrip,
                            terminal = item.terminalRoundTrip,

                            // Save data from one trip
                            airlineCodeRoundTrip = item.airlineCode,
                            airlineNameRoundTrip = item.airlineName,
                            arrivalAirportNameRoundTrip = item.arrivalAirportName,
                            arrivalIATACodeRoundTrip = item.arrivalIATACode,
                            arrivalTimeRoundTrip = item.arrivalTime,
                            departureAirportNameRoundTrip = item.departureAirportName,
                            departureIATACodeRoundTrip = item.departureIATACode,
                            departureTimeRoundTrip = item.departureTime,
                            flightCodeRoundTrip = item.flightCode,
                            flightDescriptionRoundTrip = item.flightDescription,
                            flightDurationRoundTrip = item.flightDuration,
                            flightDurationFormatRoundTrip = item.flightDurationFormat,
                            flightIdRoundTrip = item.flightId,
                            flightStatusRoundTrip = item.flightStatus,
                            flightSeatRoundTrip = item.flightSeat,
                            flightArrivalDateRoundTrip = item.flightArrivalDate,
                            flightDepartureDateRoundTrip = item.flightDepartureDate,
                            planeTypeRoundTrip = item.planeType,
                            priceAdultRoundTrip = item.priceAdult,
                            priceBabyRoundTrip = item.priceBaby,
                            priceChildRoundTrip = item.priceChild,
                            priceTotalRoundTrip = item.priceTotal,
                            paymentPriceRoundTrip = item.paymentPrice,
                            seatsAvailableRoundTrip = item.seatsAvailable,
                            terminalRoundTrip = item.terminal
                        ),
                    )
                }
            }
    }

    companion object {
        const val EXTRAS = "EXTRAS"
        const val EXTRA_FLIGHT = "extra_flight"

//        fun startActivity(
//            context: Context,
//            flight: Flight,
//        ) {
//            val intent = Intent(context, FlightResultActivity::class.java)
//            intent.putExtra(EXTRAS, flight)
//            context.startActivity(intent)
//        }

        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,
        ) {
            val intent = Intent(context, FlightResultActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }


}