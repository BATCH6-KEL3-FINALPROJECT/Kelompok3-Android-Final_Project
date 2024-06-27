package com.project.skypass.presentation.history.detailhistory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.project.skypass.R
import com.project.skypass.data.model.History
import com.project.skypass.data.model.TicketHistory
import com.project.skypass.databinding.ActivityDetailHistoryBinding
import com.project.skypass.databinding.ActivityFlightDetailBinding
import com.project.skypass.presentation.flight.detail.adapter.OnItemClickedListener
import com.project.skypass.presentation.history.HistoryViewModel
import com.project.skypass.presentation.history.detailhistory.adapter.DetailHistoryAdapter
import com.project.skypass.presentation.history.detailhistory.adapter.OnItemDetailClickedListener
import com.project.skypass.utils.proceedWhen
import com.project.skypass.utils.toIndonesianFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHistoryActivity : AppCompatActivity() {
    private val binding: ActivityDetailHistoryBinding by lazy {
        ActivityDetailHistoryBinding.inflate(layoutInflater)
    }
    private lateinit var detailHistoryAdapter: DetailHistoryAdapter

    private val viewModel: DetailHistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
        getArgumentData()
        setOnclickListener()
    }

    private fun setOnclickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun onItemClick(item: TicketHistory) {

    }

    private fun setAdapter(){
        val itemClickListener = object : OnItemDetailClickedListener<TicketHistory> {
            override fun onItemClicked(item: TicketHistory) {
                onItemClick(item)
            }
        }
        detailHistoryAdapter = DetailHistoryAdapter(itemClickListener)
        binding.rvInfoDetail.adapter = detailHistoryAdapter
    }

    private fun getArgumentData() {

        intent.extras?.getParcelable<History>(EXTRA_USER_HISTORY)?.let {
            setDetailHistoryById(it.bookingId)
            setProfileData(it)
        }
    }

    private fun setDetailHistoryById(id: String){
        viewModel.getDetailHistory(viewModel.getToken(), id).observe(this){
            it.proceedWhen(
                doOnSuccess = { result ->
                    result.payload?.let {
                        Toast.makeText(this, it.bookingDate, Toast.LENGTH_SHORT).show()
                        detailHistoryAdapter.submitData(it.ticketIdentity ?: listOf())
                    }
                },
                doOnError = {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }, doOnEmpty = {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun setProfileData(item: History) {

        binding.apply {
            tvStatusData.text = item.status
            tvBookingCode.text = item.bookingCode
            tvTimeDeparture.text = item.departureTime
            tvDateDeparture.text = item.departureDate
            tvAirportDeparture.text = item.departureAirport
            tvCityDeparture.text = item.departingAirport
            tvTimeArrival.text = item.arrivalTime
            tvFlightCode.text = item.airlineCode
            tvAirline.text = item.airlineName
            tvSeatClass.text = item.ticketIdentity?.get(0)?.seatClass
            tvDateArrival.text = item.arrivalDate
            tvAirportArrival.text = item.arrivalAirport
            tvCityArrival.text = item.arrivingAirport
            tvTotalHarga.text = item.totalPrice.toIndonesianFormat()
            tvTotal.text = "${item.noOfTickets} Penumpang"
        }
        setStatus(item.status)
        setBtnSubmit(item.status)
    }

    private fun setStatus(item: String){
        if(item == "booked"){
            binding.tvStatus.setBackgroundResource(R.color.colorSuccess)
        }else if(item == "pending"){
            binding.tvStatus.setBackgroundResource(R.color.colorFailed)
        }else{
            binding.tvStatus.setBackgroundResource(R.color.md_theme_outlineVariant_mediumContrast)
        }
    }

    private fun setBtnSubmit(item: String){
        if(item == "booked"){
            binding.btnSubmit.text = getString(R.string.text_cetak_tiket_pesawat)
        }else if(item == "pending"){
            binding.btnSubmit.text = getString(R.string.text_pesan_lagi)
            binding.btnSubmit.setOnClickListener {

            }
        }else{
            binding.btnSubmit.text = getString(R.string.text_pesan_lagi)
        }
    }

    companion object {
        const val EXTRA_USER_HISTORY = "EXTRA_USER_HISTORY"
        fun startActivity(
            context: Context,
            item: History
        ) {
            val intent = Intent(context, DetailHistoryActivity::class.java)
            intent.putExtra(EXTRA_USER_HISTORY, item)
            context.startActivity(intent)
        }
    }

}