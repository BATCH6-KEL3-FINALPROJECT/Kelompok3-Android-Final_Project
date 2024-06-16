package com.project.skypass.presentation.checkout.checkoutDataOrder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.project.skypass.data.model.OrderPassangers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutDataOrdersBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.profile.ProfileViewModelExample
import com.project.skypass.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutDataOrdersActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDataOrdersBinding.inflate(layoutInflater) }

    private val viewModel: CheckoutDataOrdersViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        setClickListeners()
        observeResult()
    }

    private fun setClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeResult() {
        addFamilyName()
        displayProfileData()
    }

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?.let {
            sendOrderData(it)
        }

    }

    private fun displayProfileData() {
        val userId = viewModel.getUserId()
        viewModel.showDataUser(userId).observe(this ){
            it.proceedWhen(
                doOnSuccess = {
                    binding.etName.setText(it.payload?.name)
                    binding.etEmail.setText(it.payload?.email)
                    binding.etNoPhone.setText(it.payload?.phoneNumber)
                },
                doOnLoading = {

                },
                doOnError = {

                }
            )
        }
    }

    private fun addFamilyName() {
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            binding.checkFamilyName.isVisible = isChecked
        }
    }

    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            if (item.supportRoundTrip == true) {
                CheckoutDataPassengerActivity.sendDataOrder(
                    this,
                    OrderUser(
                        // Change arrival into departure
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
                        isRoundTrip = item.isRoundTrip,
                        supportRoundTrip = item.supportRoundTrip,
                        orderDate = item.orderDate,

                        // Convert from arrival into departure
                        airlineCode = item.airlineCodeRoundTrip,
                        airlineName = item.airlineNameRoundTrip,
                        arrivalAirportName = item.arrivalAirportNameRoundTrip,
                        arrivalIATACode = item.arrivalIATACodeRoundTrip,
                        arrivalTime = item.arrivalTimeRoundTrip,
                        departureAirportName = item.departureAirportNameRoundTrip,
                        departureIATACode = item.departureIATACodeRoundTrip,
                        departureTime = item.departureTimeRoundTrip,
                        flightCode = item.flightCodeRoundTrip,
                        flightDescription = item.flightDescriptionRoundTrip,
                        flightDuration = item.flightDurationRoundTrip,
                        flightDurationFormat = item.flightDurationFormatRoundTrip,
                        flightId = item.flightIdRoundTrip,
                        flightStatus = item.flightStatusRoundTrip,
                        flightSeat = item.flightSeatRoundTrip,
                        flightDepartureDate = item.flightDepartureDateRoundTrip,
                        flightArrivalDate = item.flightArrivalDateRoundTrip,
                        planeType = item.planeTypeRoundTrip,
                        priceAdult = item.priceAdultRoundTrip,
                        priceBaby = item.priceBabyRoundTrip,
                        priceChild = item.priceChildRoundTrip,
                        priceTotal = item.priceTotalRoundTrip,
                        paymentPrice = item.paymentPriceRoundTrip,
                        seatsAvailable = item.seatsAvailableRoundTrip,
                        terminal = item.terminalRoundTrip,

                        // Convert from arrival into departure
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
                        flightDepartureDateRoundTrip = item.flightDepartureDate,
                        flightArrivalDateRoundTrip = item.flightArrivalDate,
                        planeTypeRoundTrip = item.planeType,
                        priceAdultRoundTrip = item.priceAdult,
                        priceBabyRoundTrip = item.priceBaby,
                        priceChildRoundTrip = item.priceChild,
                        priceTotalRoundTrip = item.priceTotal,
                        paymentPriceRoundTrip = item.paymentPrice,
                        seatsAvailableRoundTrip = item.seatsAvailable,
                        terminalRoundTrip = item.terminal,
                        ), OrderPassangers(
                        name = binding.etName.text.toString(),
                        email = binding.etEmail.text.toString(),
                        familyName = binding.etFamilyName.text.toString(),
                        noTelephone = binding.etNoPhone.text.toString(),
                        passengers = null
                    )
                )
            } else {
                CheckoutDataPassengerActivity.sendDataOrder(
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
                    OrderPassangers(
                        name = binding.etName.text.toString(),
                        email = binding.etEmail.text.toString(),
                        familyName = binding.etFamilyName.text.toString(),
                        noTelephone = binding.etNoPhone.text.toString(),
                        passengers = null
                    )
                )
            }
        }
    }

    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser,

        ) {
            val intent = Intent(context, CheckoutDataOrdersActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)

            context.startActivity(intent)
        }
    }
}