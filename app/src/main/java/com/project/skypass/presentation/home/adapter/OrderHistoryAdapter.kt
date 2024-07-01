package com.project.skypass.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ItemLastSearchBinding
import com.project.skypass.presentation.flight.filter.adapter.ViewHolderBinder

class OrderHistoryAdapter(private val orderHistoryListener: OrderHistoryItemListener? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<OrderUser>() {
                override fun areItemsTheSame(
                    oldItem: OrderUser,
                    newItem: OrderUser,
                ): Boolean {
                    return oldItem.id == newItem.id && oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: OrderUser,
                    newItem: OrderUser,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(
        data: List<OrderUser>,
        cek: Double,
    ) {
        dataDiffer.submitList(data.reversed())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return CartViewHolder(
            ItemLastSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            orderHistoryListener,
        )
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        (holder as ViewHolderBinder<OrderUser>).bind(dataDiffer.currentList[position])
    }
}

class CartViewHolder(
    private val binding: ItemLastSearchBinding,
    private val orderHistoryListener: OrderHistoryItemListener?,
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<OrderUser> {
    override fun bind(item: OrderUser) {
        setCartData(item)
    }

    private fun setCartData(item: OrderUser) {
        with(binding) {
            tvDeparture.text = item.departureCity
            tvArrival.text = item.arrivalCity
            tvDateOrder.text = item.orderDate
            tvContentDatePassengerClass.text = item.passengersTotal + " Orang - " + item.seatClass
        }
    }

}

interface OrderHistoryItemListener {
    fun onRemoveCartClicked(item: OrderUser)
}
