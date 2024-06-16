package com.project.skypass.presentation.checkout.checkoutDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ActivityCheckoutDetailBinding
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.utils.toIndonesianFormat

class CheckoutDetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCheckoutDetailBinding.inflate(layoutInflater) }
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
    private fun observeResult(){
//        observe view model
    }
    private fun getArgumentData() {
        intent.extras?.getParcelable<OrderUser>(EXTRA_FLIGHT)?. let {
            setProfileData(it)
            sendOrderData(it)
        }
    }
    private fun setProfileData(item: OrderUser) {
        binding.apply {
            if (item.isRoundTrip == false && item.supportRoundTrip == true) {
                tvTotalPrice.text = (item.priceTotal?.plus(item.priceTotalRoundTrip!!)).toIndonesianFormat()
                // change departure to arrival
                rvTicketDetail.tvAirportDeparture.text = item.departureAirportNameRoundTrip
                rvTicketDetail.tvAirportArrival.text = item.arrivalAirportNameRoundTrip
                rvTicketDetail.tvAirline.text = item.airlineNameRoundTrip
                rvTicketDetail.tvCityDeparture.text = item.arrivalCity
                rvTicketDetail.tvCityArrival.text = item.departureCity
                rvTicketDetail.tvDateDeparture.text = item.flightDepartureDateRoundTrip
                rvTicketDetail.tvDateArrival.text = item.flightArrivalDateRoundTrip
                rvTicketDetail.tvTimeDeparture.text = item.departureTimeRoundTrip
                rvTicketDetail.tvTimeArrival.text = item.arrivalTimeRoundTrip
                rvTicketDetail.tvInfoDetail.text = item.flightDescriptionRoundTrip
                rvTicketDetail.tvFlightCode.text = item.flightCodeRoundTrip

                rvTicketDetailRound.tvAirportDeparture.text = item.departureAirportName
                rvTicketDetailRound.tvAirportArrival.text = item.arrivalAirportName
                rvTicketDetailRound.tvAirline.text = item.airlineName
                rvTicketDetailRound.tvCityDeparture.text = item.departureCity
                rvTicketDetailRound.tvCityArrival.text = item.arrivalCity
                rvTicketDetailRound.tvDateDeparture.text = item.flightDepartureDate
                rvTicketDetailRound.tvDateArrival.text = item.flightArrivalDate
                rvTicketDetailRound.tvTimeDeparture.text = item.departureTime
                rvTicketDetailRound.tvTimeArrival.text = item.arrivalTime
                rvTicketDetailRound.tvInfoDetail.text = item.flightDescription
                rvTicketDetailRound.tvFlightCode.text = item.flightCode
                rvTicketDetailRound.root.isVisible = true
            }else{
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
            }
            //set price detail
            tvAdultCount.text = item.passengersAdult.toString()
            tvChildCount.text = item.passengersChild.toString()
            tvBabyCount.text = item.passengersBaby.toString()
            val priceAdult = item.priceTotal?.times(item.passengersAdult!!)?.plus(item.priceTotalRoundTrip?.times(item.passengersAdult!!)!!)
            val priceChild = item.priceTotal?.times(item.passengersChild!!)?.plus(item.priceTotalRoundTrip?.times(item.passengersChild!!)!!)
            val priceBaby = (item.priceTotal!! /2).times(item.passengersChild!!).plus((item.priceTotalRoundTrip!!/2).times(item.passengersChild!!))
            val priceTotal = priceAdult!! + priceChild!! + priceBaby
            tvPriceAdult.text = "${priceAdult.toIndonesianFormat()}"
            tvPricechild.text = "${priceChild.toIndonesianFormat()}"
            tvPriceBaby.text = "$priceBaby"
            tvTotalPrice.text = "${priceTotal.toIndonesianFormat()}"
        }
    }
    private fun sendOrderData(item: OrderUser) {
        binding.btnSubmit.setOnClickListener {
            MainActivity.sendDataOrder(
                this,
                item,
            )
        }
    }
    companion object {
        const val EXTRA_FLIGHT = "extra_flight"
        fun sendDataOrder(
            context: Context,
            orderUser: OrderUser
        ){
            val intent = Intent(context, CheckoutDetailActivity::class.java)
            intent.putExtra(EXTRA_FLIGHT, orderUser)
            context.startActivity(intent)
        }
    }
}