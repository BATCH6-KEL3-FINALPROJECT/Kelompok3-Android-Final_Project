package com.project.skypass.presentation.checkout.checkoutSeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.skypass.R
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutSeatBinding
import com.project.skypass.databinding.ActivityMainBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutDetail.CheckoutDetailActivity
import com.project.skypass.presentation.flight.result.FlightResultActivity
import dev.jahidhasanco.seatbookview.SeatBookView

class CheckoutSeatActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCheckoutSeatBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setClickListeners()
        setupSeatBookView()
    }
    private fun setClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
    private fun observeResult(){
//        observe view model
    }

    private var seats = (
            "/_____" +
                    "/AA_AA" +
                    "/AA_AA" +
                    "/AA_AA" +
                    "/RU_AA" +
                    "/AA_AR" +
                    "/AU_AA" +
                    "/AA_AA" +
                    "/AAAAA"
            )

    private var title = listOf(
        "/", "", "", "", "", "",
        "/", "A1", "A2", "", "A3", "A4",
        "/", "B1", "B2", "", "B3", "B4",
        "/", "C1", "C2", "", "C3", "C4",
        "/", "D1", "D2", "", "D3", "D4",
        "/", "E1", "E2", "", "E3", "E4",
        "/", "F1", "F2", "", "F3", "F4",
        "/", "G1", "G2", "", "G3", "G4",
        "/", "H1", "H2", "H3", "H4", "H5"
    )

    private fun setupSeatBookView() {
        val seatBookView: SeatBookView = binding.layoutSeat
        seatBookView.setSeatsLayoutString(seats)
            .isCustomTitle(true)
            .setCustomTitle(title)
            .setSeatLayoutPadding(0)
            .setSeatSizeBySeatsColumnAndLayoutWidth(6, -1)

        seatBookView.show()

    }
    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(CheckoutSeatActivity.EXTRA_FLIGHT)?. let {
            sendOrderData(it)
        }
    }
    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            CheckoutDetailActivity.sendDataOrder(
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
        }
    }

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser
        ){
            val intent = Intent(context, CheckoutSeatActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }






}