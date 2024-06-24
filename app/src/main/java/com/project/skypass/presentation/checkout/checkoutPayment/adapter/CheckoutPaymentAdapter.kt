package com.project.skypass.presentation.checkout.checkoutPayment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Flight
import com.project.skypass.data.model.OrderPassengers
import com.project.skypass.data.model.PassengersData
import com.project.skypass.data.source.network.model.checkout.request.PassengerData
import com.project.skypass.data.source.network.model.history.detailhistory.Passenger
import com.project.skypass.databinding.ItemCheckoutRvPaymentBinding
import com.project.skypass.databinding.ItemFlightTicketBinding
import com.project.skypass.utils.toIndonesianFormat


class CheckoutPaymentAdapter(
    private val listener: OnItemClickedPaymentListener<PassengersData>
) :
    RecyclerView.Adapter<CheckoutPaymentAdapter.PaymentCheckoutViewHolder>() {
    private val data = mutableListOf<PassengersData>()

    fun submitData(items: List<PassengersData>) {
        items.forEach { newItem ->
            if (!data.contains(newItem)) {
                data.add(newItem)
            }
        }
    }

    private var onTicketClickListener: ((PassengersData) -> Unit)? = null

    fun setOnTicketClickListener(listener: (PassengersData) -> Unit) {
        onTicketClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PaymentCheckoutViewHolder {
        return PaymentCheckoutViewHolder(
            ItemCheckoutRvPaymentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener,
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: PaymentCheckoutViewHolder,
        position: Int,
    ) {
        holder.bind(data[position])
    }

    class PaymentCheckoutViewHolder(
        private val binding: ItemCheckoutRvPaymentBinding,
        private val listener: OnItemClickedPaymentListener<PassengersData>,

        ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PassengersData) {
            binding.tvName.text = " ${item.title} ${item.firstName} ${item.lastName}"

            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }

    }
}

interface OnItemClickedPaymentListener<T> {
    fun onItemClicked(item: T)
}
