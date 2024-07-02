package com.project.skypass.presentation.checkout.checkoutPayment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.PassengersData
import com.project.skypass.databinding.ItemCheckoutRvPaymentBinding

class CheckoutPaymentAdapter(
    private val listener: OnItemClickedPaymentListener<PassengersData>,
) : RecyclerView.Adapter<CheckoutPaymentAdapter.PaymentCheckoutViewHolder>() {
    private val data = mutableListOf<PassengersData>()

    fun submitData(items: List<PassengersData>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CheckoutPaymentAdapter.PaymentCheckoutViewHolder {
        return PaymentCheckoutViewHolder(
            ItemCheckoutRvPaymentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener,
        )
    }

    override fun onBindViewHolder(
        holder: PaymentCheckoutViewHolder,
        position: Int,
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class PaymentCheckoutViewHolder(
        private val binding: ItemCheckoutRvPaymentBinding,
        private val listener: OnItemClickedPaymentListener<PassengersData>,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PassengersData) {
            binding.tvName.text = " ${item.title} ${item.firstName} ${item.lastName}"
            binding.tvSeatNumber.text = item.passengerType
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}

interface OnItemClickedPaymentListener<T> {
    fun onItemClicked(item: T)
}