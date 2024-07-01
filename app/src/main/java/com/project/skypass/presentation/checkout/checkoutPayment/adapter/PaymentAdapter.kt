package com.project.skypass.presentation.checkout.checkoutPayment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Booking
import com.project.skypass.databinding.ItemCheckoutPaymentTicketBinding
import com.project.skypass.presentation.checkout.checkoutPayment.adapter.PaymentAdapter.PaymentViewHolder
import com.project.skypass.utils.toIndonesianFormat

class PaymentAdapter() : RecyclerView.Adapter<PaymentViewHolder>() {
    private val data = mutableListOf<Booking>()

    fun submitDataPayment(items: List<Booking>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaymentViewHolder {
        return PaymentViewHolder(
            ItemCheckoutPaymentTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(
        holder: PaymentViewHolder,
        position: Int,
    ) {
        holder.bind(data[position])
    }

    class PaymentViewHolder(
        private val binding: ItemCheckoutPaymentTicketBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Booking) {
            binding.tvPassangersList.text = "${item.totalAdult} adult, ${item.totalChild} child, ${item.totalBaby}, baby"
            binding.tvCityName.text = item.departureCity
            binding.tvCityNameDestination.text = item.arrivalCity
            binding.tvTimeDeparture.text = item.departureTime
            binding.tvDateDeparture.text = item.departureDate
            binding.tvTimeArrival.text = item.arrivalTime
            binding.tvDateArrival.text = item.arrivalDate
            binding.tvIdBackingCode.text = item.bookingCode
            binding.tvClass.text = item.seatClass
            binding.tvTotalPrice.text = item.totalPrice.toIndonesianFormat()
        }
    }
}
