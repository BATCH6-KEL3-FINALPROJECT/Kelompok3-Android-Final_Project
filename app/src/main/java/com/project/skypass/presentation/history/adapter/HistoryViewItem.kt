package com.project.skypass.presentation.history.adapter

import android.view.View
import com.project.skypass.R
import com.project.skypass.data.model.History
import com.project.skypass.databinding.ItemMounthHistoryOrderBinding
import com.project.skypass.databinding.ItemTicketHistoryOrderBinding
import com.project.skypass.presentation.history.detailhistory.DetailHistoryActivity
import com.project.skypass.utils.convertDateText
import com.project.skypass.utils.convertDateTextApi
import com.project.skypass.utils.convertMinutesToHours
import com.project.skypass.utils.toIndonesianFormat
import com.xwray.groupie.viewbinding.BindableItem

class HistoryMonthItem(private val month: String) : BindableItem<ItemMounthHistoryOrderBinding>() {
    override fun bind(
        viewBinding: ItemMounthHistoryOrderBinding,
        position: Int,
    ) {
        viewBinding.tvMount.text = month
    }

    override fun getLayout() = R.layout.item_mounth_history_order

    override fun initializeViewBinding(view: View): ItemMounthHistoryOrderBinding {
        return ItemMounthHistoryOrderBinding.bind(view)
    }
}

class HistoryTicketItem(private val item: History) : BindableItem<ItemTicketHistoryOrderBinding>() {
    override fun bind(
        viewBinding: ItemTicketHistoryOrderBinding,
        position: Int,
    ) {
        viewBinding.tvStatusData.text = item.status
        viewBinding.tvOrderDateData.text = convertDateTextApi(item.bookingDate)
        viewBinding.tvCityNameDestinationAlias.text = item.arrivingAirport
        viewBinding.tvCityDepartureAlias.text = item.departingAirport
        viewBinding.tvTimeDeparture.text = item.departureTime
        viewBinding.tvDateDeparture.text = convertDateText(item.departureDate)
        viewBinding.tvTimeArrival.text = item.arrivalTime
        viewBinding.tvDateArrival.text = convertDateText(item.arrivalDate)
        viewBinding.tvIdBackingCode.text = item.bookingCode
        viewBinding.tvPrice.text = item.totalPrice.toIndonesianFormat()
        setStatus(item.status, viewBinding)
        convertMinutesData(item, viewBinding)
        viewBinding.root.setOnClickListener {
            navigateToDetail(item, viewBinding)
        }
    }

    private fun convertMinutesData(
        item: History,
        binding: ItemTicketHistoryOrderBinding,
    ) {
        item.flightDuration.let { duration ->
            val (hours, remainingMinutes) = convertMinutesToHours(duration)
            val durationText =
                if (hours > 0) {
                    "$hours Jam $remainingMinutes Menit"
                } else {
                    "$remainingMinutes Menit"
                }
            binding.tvLengthOfJourney.text = durationText
        }
    }

    override fun getLayout() = R.layout.item_ticket_history_order

    override fun initializeViewBinding(view: View): ItemTicketHistoryOrderBinding {
        return ItemTicketHistoryOrderBinding.bind(view)
    }

    private fun navigateToDetail(
        item: History,
        binding: ItemTicketHistoryOrderBinding,
    ) {
        DetailHistoryActivity.startActivity(
            binding.root.context,
            item,
        )
    }

    private fun setStatus(
        status: String,
        binding: ItemTicketHistoryOrderBinding,
    ) {
        if (status == "booked") {
            binding.tvStatus.setBackgroundResource(R.color.colorSuccess)
        } else if (status == "pending") {
            binding.tvStatus.setBackgroundResource(R.color.colorFailed)
        } else {
            binding.tvStatus.setBackgroundResource(R.color.md_theme_outlineVariant_mediumContrast)
        }
    }
}
