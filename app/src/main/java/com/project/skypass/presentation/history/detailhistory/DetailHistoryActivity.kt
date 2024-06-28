package com.project.skypass.presentation.history.detailhistory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.project.skypass.R
import com.project.skypass.data.model.History
import com.project.skypass.data.model.TicketHistory
import com.project.skypass.databinding.ActivityDetailHistoryBinding
import com.project.skypass.databinding.ActivityFlightDetailBinding
import com.project.skypass.presentation.checkout.checkoutmidtrans.CheckoutMidtransActivity
import com.project.skypass.presentation.flight.detail.adapter.OnItemClickedListener
import com.project.skypass.presentation.history.HistoryViewModel
import com.project.skypass.presentation.history.detailhistory.adapter.DetailHistoryAdapter
import com.project.skypass.presentation.history.detailhistory.adapter.OnItemDetailClickedListener
import com.project.skypass.presentation.main.MainActivity
import com.project.skypass.presentation.profile.changeprofile.ChangeProfileActivity
import com.project.skypass.utils.proceedWhen
import com.project.skypass.utils.toIndonesianFormat
import io.github.muddz.styleabletoast.StyleableToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailHistoryActivity : AppCompatActivity() {
    private val binding: ActivityDetailHistoryBinding by lazy {
        ActivityDetailHistoryBinding.inflate(layoutInflater)
    }
    private lateinit var detailHistoryAdapter: DetailHistoryAdapter
    private val viewModel: DetailHistoryViewModel by viewModel()
    private var dataEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dataEmail = displayProfileData()
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
                        detailHistoryAdapter.submitData(it.ticketIdentity ?: listOf())
                    }
                },
                doOnError = {
                }, doOnEmpty = {
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
        setBtnSubmit(item)
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

    private fun setBtnSubmit(item: History){
        if(item.status == "booked"){
            binding.btnSubmit.text = getString(R.string.text_cetak_tiket_pesawat)
            binding.btnSubmit.setOnClickListener {
                viewModel.printTicket(viewModel.getToken(), item.bookingId, dataEmail).observe(this){
                    it.proceedWhen(
                        doOnSuccess = {
                            StyleableToast.makeText(this, "berhasil dikirim di Email", R.style.ToastSuccess).show()
                        }, doOnError = {
                            StyleableToast.makeText(this, "gagal dikirim", R.style.ToastError).show()
                        }, doOnLoading = {
                            StyleableToast.makeText(this, "loading...", R.style.ToastSuccess).show()
                        }
                    )
                }

            }
        }else if(item.status == "pending"){
            binding.btnSubmit.text = getString(R.string.text_history_pendding_button)
            binding.btnSubmit.setOnClickListener {
                viewModel.createPayment(
                    viewModel.getToken(),
                    item.paymentId
                ).observe(this){
                    it.proceedWhen(
                        doOnSuccess = { success ->
                            navigateToMidtrans(success.payload?.urlMidtrans!!)
                        },
                        doOnLoading = {

                        },
                        doOnError = {
                        }
                    )
                }
            }
        }else{
            binding.btnSubmit.text = getString(R.string.text_pesan_lagi)
            binding.btnSubmit.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun displayProfileData(): String{
        var dataEmail = "rsyah1641@gmail.com"
        val userId = viewModel.getUserId()
        viewModel.showDataUser(userId).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    it.payload?.let { user ->
                        dataEmail = user.email.toString()
                    }
                },
                doOnLoading = {
                },
                doOnError = {
                }
            )
        }
        return dataEmail
    }

    private fun navigateToMidtrans(paymentId: String) {
        startActivity(
            Intent(this, CheckoutMidtransActivity::class.java)
                .putExtra(CheckoutMidtransActivity.EXTRA_MIDTRANS, paymentId)
        )
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