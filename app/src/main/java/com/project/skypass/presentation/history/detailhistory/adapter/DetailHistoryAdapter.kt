package com.project.skypass.presentation.history.detailhistory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.TicketHistory
import com.project.skypass.databinding.ItemListPassengersDetailHistoryBinding


class DetailHistoryAdapter(
    private val listener: OnItemDetailClickedListener<TicketHistory>
) :
    RecyclerView.Adapter<DetailHistoryAdapter.DetailHistoryViewHolder>() {
    private val data = mutableListOf<TicketHistory>()

    fun submitData(items: List<TicketHistory>) {
        items.forEach { newItem ->
            if (!data.contains(newItem)) {
                data.add(newItem)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DetailHistoryViewHolder {
        return DetailHistoryViewHolder(
            ItemListPassengersDetailHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener,
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: DetailHistoryViewHolder,
        position: Int,
    ) {
        holder.bind(data[position])
    }

    class DetailHistoryViewHolder(
        private val binding: ItemListPassengersDetailHistoryBinding,
        private val listener: OnItemDetailClickedListener<TicketHistory>,

        ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketHistory) {
            binding.tvNamePassengers.text = "${item.passengerFirstName}" + "  ${item.passengerLastName}"
            binding.idPassengers.text = item.passengerId

            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}

interface OnItemDetailClickedListener<T> {
    fun onItemClicked(item: T)
}