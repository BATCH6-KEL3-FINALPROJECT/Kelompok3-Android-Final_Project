package com.project.skypass.presentation.flight.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Flight
import com.project.skypass.databinding.ItemFlightTicketBinding
import com.project.skypass.presentation.flight.detail.adapter.FlightDetailAdapter
import com.project.skypass.presentation.flight.detail.adapter.OnItemClickedListener
import com.project.skypass.utils.toIndonesianFormat

class FlightDetailAdapter(
    private val listener: OnItemClickedListener<Flight>
) :
    RecyclerView.Adapter<FlightDetailAdapter.FlightViewHolder>() {
    private val data = mutableListOf<Flight>()

    fun submitData(items: List<Flight>) {
        /*items.forEach { newItem ->
            if (!data.contains(newItem)) {
                data.clear()
                data.add(newItem)
                notifyDataSetChanged()
            }
        }*/
        data.clear()  // Clear existing data
        data.addAll(items)  // Add new items
        notifyDataSetChanged()  // Notify adapter of data changes
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
            ItemFlightTicketBinding.inflate(
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
        private val binding: ItemFlightTicketBinding,
        private val listener: OnItemClickedListener<Flight>,

        ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Flight) {
            binding.tvPrice.text = item.price.toIndonesianFormat()
            binding.tvAirline.text = item.airlineName
            binding.tvCityName.text = item.departureCity
            binding.tvCityNameAlias.text = item.departureIATACode
            binding.tvCityNameDestination.text = item.arrivalCity
            binding.tvCityNameDestinationAlias.text = item.arrivalIATACode
            binding.tvDateDeparture.text = item.departureDate
            binding.tvDateArrival.text = item.arrivalDate
            binding.tvTimeDeparture.text = item.departureTime
            binding.tvTimeArrival.text = item.arrivalTime

            item.flightDuration?.let { duration ->
                val (hours, remainingMinutes) = convertMinutesToHours(duration)
                val durationText = if (hours > 0) {
                    "$hours Jam $remainingMinutes Menit"
                } else {
                    "$remainingMinutes Menit"
                }
                binding.tvLengthOfJourney.text = durationText
            } ?: run {
                binding.tvLengthOfJourney.text = "N/A"
            }

            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }

        private fun convertMinutesToHours(minutes: Int): Pair<Int, Int> {
            val hours = minutes / 60
            val remainingMinutes = minutes % 60
            return Pair(hours, remainingMinutes)
        }
    }
}

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}


