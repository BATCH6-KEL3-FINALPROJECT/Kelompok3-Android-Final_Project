package com.project.skypass.presentation.flight.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.data.model.Flight
import com.project.skypass.databinding.ActivityFlightResultBinding
import com.project.skypass.utils.toIndonesianFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FlightResultActivity : AppCompatActivity() {
    private val binding: ActivityFlightResultBinding by lazy {
        ActivityFlightResultBinding.inflate(layoutInflater)
    }
    private lateinit var flight: Flight
    private val flightResultViewModel: FlightResultViewModel by viewModel {
        parametersOf(intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        flightResultViewModel.flight?.let { bindFlightData(it) }
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivBackDetail.setOnClickListener {
            onBackPressed()
        }
    }

    private fun bindFlightData(flight: Flight) {
        this@FlightResultActivity.flight = intent.getParcelableExtra<Flight>("EXTRAS") ?: return
        flight.let { item ->
            binding.tvTotalPrice.text = item.price.toIndonesianFormat()
            binding.rvTicketDetail.tvAirportDeparture.text = item.departureAirportName
            binding.rvTicketDetail.tvAirportArrival.text = item.arrivalAirportName
            binding.rvTicketDetail.tvAirline.text = item.airlineName
            binding.rvTicketDetail.tvCityDeparture.text = item.departureCity
            binding.rvTicketDetail.tvCityArrival.text = item.arrivalCity
            binding.rvTicketDetail.tvDateDeparture.text = item.departureDate
            binding.rvTicketDetail.tvDateArrival.text = item.arrivalDate
            binding.rvTicketDetail.tvTimeDeparture.text = item.departureTime
            binding.rvTicketDetail.tvTimeArrival.text = item.arrivalTime
            binding.rvTicketDetail.tvInfoDetail.text = item.flightDescription
            binding.rvTicketDetail.tvFlightCode.text = item.flightCode
        }
    }

    companion object {
        const val EXTRAS = "EXTRAS"

        fun startActivity(
            context: Context,
            flight: Flight,
        ) {
            val intent = Intent(context, FlightResultActivity::class.java)
            intent.putExtra(EXTRAS, flight)
            context.startActivity(intent)
        }
    }
}