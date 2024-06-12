package com.project.skypass.presentation.flight.result.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Flight
import com.project.skypass.databinding.ItemFlightTicketDetailBinding
import com.project.skypass.presentation.flight.result.adapter.OnItemClickedListener

class FlightResultAdapter(
    private val listener: OnItemClickedListener<Flight>,
) :
    RecyclerView.Adapter<FlightResultAdapter.FlightViewHolder>() {
    private val data = mutableListOf<Flight>()

    fun submitData(items: List<Flight>) {
        items.forEach { newItem ->
            if (!data.contains(newItem)) {
                data.add(newItem)
            }
        }
    }

    private var onTicketClickListener: ((Flight) -> Unit)? = null

    fun setOnTicketClickListener(listener: (Flight) -> Unit) {
        onTicketClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FlightViewHolder {
        return FlightViewHolder(
            ItemFlightTicketDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener,
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: FlightViewHolder,
        position: Int,
    ) {
        holder.bind(data[position])
    }

    class FlightViewHolder(
        private val binding: ItemFlightTicketDetailBinding,
        private val listener: OnItemClickedListener<Flight>,
        ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Flight) {
            binding.tvAirportDeparture.text = item.departureAirportName
            binding.tvAirportArrival.text = item.arrivalAirportName
            binding.tvAirline.text = item.airlineName
            binding.tvCityDeparture.text = item.departureCity
            binding.tvCityArrival.text = item.arrivalCity
            binding.tvDateDeparture.text = item.departureDate
            binding.tvDateArrival.text = item.arrivalDate
            binding.tvTimeDeparture.text = item.departureTime
            binding.tvTimeArrival.text = item.arrivalTime

            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}