package com.project.skypass.presentation.history.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import com.project.skypass.R
import com.project.skypass.data.model.History
import com.project.skypass.data.model.Notification
import com.project.skypass.databinding.ItemMounthHistoryOrderBinding
import com.project.skypass.databinding.ItemTicketHistoryOrderBinding
import com.project.skypass.presentation.history.detailhistory.DetailHistoryActivity
import com.project.skypass.presentation.notification.detailNotification.DetailNotificationActivity
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
        viewBinding.tvStatus.text = item.status
        viewBinding.tvClassAirplane.text = item.departingAirport
        viewBinding.tvCityNameDestinationAlias.text = item.arrivalAirport
        viewBinding.tvCityNameDestination.text = item.arrivalAirport
        viewBinding.tvCityDeparture.text = item.departureAirport
        viewBinding.tvCityDepartureAlias.text = item.departureAirport
        viewBinding.tvLengthOfJourney.text = item.flightDuration.toString()
        viewBinding.tvTimeDeparture.text = item.departureTime
        viewBinding.tvDateDeparture.text = item.departureDate
        viewBinding.tvTimeArrival.text = item.arrivalTime
        viewBinding.tvDateArrival.text = item.arrivalDate
        viewBinding.tvIdBackingCode.text = item.bookingId
        viewBinding.tvPrice.text = item.totalPrice
        setStatus(item.status, viewBinding)
    }

    private fun navigateToDetail(item: History, binding: ItemTicketHistoryOrderBinding) {
        DetailHistoryActivity.startActivity(
            binding.root.context, History(item)
        )
    }

    override fun getLayout() = R.layout.item_ticket_history_order

    override fun initializeViewBinding(view: View): ItemTicketHistoryOrderBinding {
        return ItemTicketHistoryOrderBinding.bind(view)
    }

    private fun setStatus(
        status: String,
        binding: ItemTicketHistoryOrderBinding,
    ) {
        if (status == "issued") {
            binding.tvStatus.setBackgroundResource(R.color.colorSuccess)
        } else if (status == "unpaid") {
            binding.tvStatus.setBackgroundResource(R.color.colorFailed)
        }else{
            binding.tvStatus.setBackgroundResource(R.color.md_theme_outlineVariant_mediumContrast)
        }
    }
}