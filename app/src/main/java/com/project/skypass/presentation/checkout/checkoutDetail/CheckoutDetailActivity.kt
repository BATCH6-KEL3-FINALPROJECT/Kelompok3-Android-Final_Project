package com.project.skypass.presentation.checkout.checkoutDetail

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.project.skypass.R
import com.project.skypass.core.BaseActivity
import com.project.skypass.data.model.CheckoutPayment
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.OrderUser
import com.project.skypass.data.model.PassengersData
import com.project.skypass.databinding.ActivityCheckoutDetailBinding
import com.project.skypass.databinding.LayoutStateErrorBinding
import com.project.skypass.databinding.LayoutStateLoadingBinding
import com.project.skypass.databinding.LayoutStateSuccessBinding
import com.project.skypass.presentation.checkout.checkoutDataPassenger.CheckoutDataPassengerActivity
import com.project.skypass.presentation.checkout.checkoutPayment.CheckoutPaymentActivity
import com.project.skypass.presentation.checkout.checkoutSeat.CheckoutSeatActivity
import com.project.skypass.utils.ApiErrorException
import com.project.skypass.utils.NoInternetException
import com.project.skypass.utils.UnauthorizedException
import com.project.skypass.utils.proceedWhen
import com.project.skypass.utils.toIndonesianFormat
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutDetailActivity : BaseActivity() {
    private val binding by lazy { ActivityCheckoutDetailBinding.inflate(layoutInflater) }
    private val viewModel: CheckoutDetailViewModel by viewModel()

    var priceTotal: Int? = null
    var priceAdult: Int? = null
    var priceChild: Int? = null
    var priceBaby: Int? = null

    private var dialog: Dialog? = null
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

    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?.let {

            intent.extras?.getParcelable<OrderPassengers>(CheckoutDataPassengerActivity.EXTRA_USER_ORDER)
                ?.let { orderPassenger ->
                    setProfileData(it, orderPassenger)
                    val toCheckout = sendToPayment(it, orderPassenger)
                    sendOrderData(it, toCheckout, orderPassenger)
                    Log.d(
                        "checkSeatInPassengersData",
                        "Passengers Data: ${toCheckout.passengersData}"
                    )
                }
        }
    }

    private fun sendToPayment(item: OrderUser, passengerData: OrderPassengers): CheckoutPayment {
        val totalPassengers =
            item.passengersBaby!! + item.passengersAdult!! + item.passengersChild!!
        val payment = CheckoutPayment(
            totalAmount = priceTotal!!,
            departureFlightId = item.flightId!!,
            returnFlightId = item.flightIdRoundTrip!!,
            passengersData = passengerData.passengers!!.mapIndexed { index, it ->
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
                    passengerType = it.passengerType
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

                if (item.seatsAvailableRoundTrip != null) {
                    btnSubmit.text = getString(R.string.btn_detail_checkoutBack)
                } else {
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

    private fun observeResult(
        totalAmount: Int,
        departureFlightId: String,
        returnFlightId: String?,
        fullName: String? = null,
        familyName: String? = null,
        email: String? = null,
        phone: String? = null,
        passenger: List<PassengersData>
    ) {
        viewModel.createBooking(
            viewModel.getToken(),
            totalAmount,
            departureFlightId,
            returnFlightId,
            fullName,
            familyName,
            email,
            phone,
            passenger
        ).observe(this){
            it.proceedWhen(
                doOnSuccess = { success ->
                    dialog?.dismiss()
                    doSuccess()
                    CheckoutPaymentActivity.sendDataOrder(
                        this,
                        it.payload?.data?.bookingResult?.paymentId!!,
                        passenger
                    )
                },
                doOnLoading = {
                    dialog?.dismiss()
                    doLoading()
                },
                doOnError = {
                    dialog?.dismiss()
                    if (it.exception is ApiErrorException) {
                        val errorMessage = it.exception.errorResponse
                        StyleableToast.makeText(this, errorMessage.message, Toast.LENGTH_SHORT).show()
                    } else if (it.exception is NoInternetException) {
                        StyleableToast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
                    } else if (it.exception is UnauthorizedException) {
                        val errorMessage = it.exception.errorUnauthorizedResponse
                        StyleableToast.makeText(this, errorMessage.message, Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch {
                            delay(2000)
                            handleUnAuthorize()
                        }
                    } else {
                        doError()
                    }
                }
            )
        }
    }

    private fun doLoading(){
        val dialogBinding = LayoutStateLoadingBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun doSuccess(){
        val dialogBinding = LayoutStateSuccessBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun doError(){
        val dialogBinding = LayoutStateErrorBinding.inflate(layoutInflater)
        dialog = Dialog(this).apply {
            setCancelable(true)
            setContentView(dialogBinding.root)
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun sendOrderData(item: OrderUser, passengerData: CheckoutPayment, orderPassenger: OrderPassengers) {
        binding.btnSubmit.setOnClickListener {
            if (item.supportRoundTrip == false) {
                /*CheckoutPaymentActivity.sendDataOrder(
                    this,
                    item, passengerData
                )*/
                observeResult(
                    passengerData.totalAmount,
                    passengerData.departureFlightId,
                    passengerData.returnFlightId,
                    orderPassenger.name,
                    orderPassenger.familyName,
                    orderPassenger.email,
                    orderPassenger.noTelephone,
                    passengerData.passengersData!!
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
                    orderPassenger
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