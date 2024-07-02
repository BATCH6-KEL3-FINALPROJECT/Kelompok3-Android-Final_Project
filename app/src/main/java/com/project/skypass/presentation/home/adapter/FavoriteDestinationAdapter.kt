package com.project.skypass.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.project.skypass.R
import com.project.skypass.data.model.Destination
import com.project.skypass.data.model.OrderUser
import com.project.skypass.databinding.ItemDestinationFavoriteBinding
import com.project.skypass.presentation.flight.detail.FlightDetailActivity
import com.project.skypass.utils.convertDestinationFavorite
import com.project.skypass.utils.formatDatesDestinationFavorite
import com.project.skypass.utils.orderDate
import com.project.skypass.utils.toIndonesianFormatDouble

class FavoriteDestinationAdapter(private val itemClick: (Destination) -> Unit) : RecyclerView.Adapter<FavoriteDestinationAdapter.DestinationViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<Destination>() {
                override fun areItemsTheSame(
                    oldItem: Destination,
                    newItem: Destination,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Destination,
                    newItem: Destination,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Destination>) {
        dataDiffer.submitList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DestinationViewHolder {
        val binding = ItemDestinationFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    override fun onBindViewHolder(
        holder: DestinationViewHolder,
        position: Int,
    ) {
        holder.bind(dataDiffer.currentList[position])
    }

    class DestinationViewHolder(
        private val binding: ItemDestinationFavoriteBinding,
        val itemClick: (Destination) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Destination) {
                binding.ivDestinationFavorite.load(data.imageUrl) {
                    crossfade(true)
                }
                binding.tvTitleDestinationFavorite.text = itemView.context.getString(R.string.from_to, data.from, data.to)
                binding.tvPassengers.text =
                    itemView.context.getString(R.string.total_passenger_fav_des, data.passengersTotal.toString())
                binding.tvPromotion.text = data.discount
                binding.tvDatePromotion.text = formatDatesDestinationFavorite(data.departureDate, data.returnDate)
                binding.tvPrice.text = itemView.context.getString(R.string.idr_rp_fav_des, data.price.toDouble().toIndonesianFormatDouble())

                itemView.setOnClickListener {
                    sendToFlight(data)
                }
        }

        private fun sendToFlight(data: Destination) {
            FlightDetailActivity.startActivity(
                binding.root.context,
                OrderUser(
                    // Home Data
                    id = (0..5000).random(),
                    arrivalCity = data.to,
                    arrivalDate = convertDestinationFavorite(data.returnDate),
                    seatClass = data.seatClass,
                    departureCity = data.from,
                    departureDate = convertDestinationFavorite(data.departureDate),
                    passengersTotal = data.passengersTotal.toString(),
                    passengersAdult = data.passengersAdult,
                    passengersBaby = data.passengersBaby,
                    passengersChild = data.passengersChild,
                    isRoundTrip = data.isRoundTrip,
                    supportRoundTrip = data.isRoundTrip,
                    orderDate = orderDate(),
                    // Flight Data (One Way)
                    airlineCode = "",
                    airlineName = "",
                    arrivalAirportName = "",
                    arrivalIATACode = "",
                    arrivalTime = "",
                    departureAirportName = "",
                    departureIATACode = "",
                    departureTime = "",
                    flightCode = "",
                    flightDescription = "",
                    flightDuration = null,
                    flightDurationFormat = "",
                    flightId = "",
                    flightStatus = "",
                    flightSeat = "",
                    flightArrivalDate = "",
                    flightDepartureDate = "",
                    planeType = "",
                    priceAdult = 0,
                    priceBaby = 0,
                    priceChild = 0,
                    priceTotal = 0,
                    paymentPrice = null,
                    seatsAvailable = null,
                    terminal = "",
                    // Flight Data (Round Trip)
                    airlineCodeRoundTrip = "",
                    airlineNameRoundTrip = "",
                    arrivalAirportNameRoundTrip = "",
                    arrivalIATACodeRoundTrip = "",
                    arrivalTimeRoundTrip = "",
                    departureAirportNameRoundTrip = "",
                    departureIATACodeRoundTrip = "",
                    departureTimeRoundTrip = "",
                    flightCodeRoundTrip = "",
                    flightDescriptionRoundTrip = "",
                    flightDurationRoundTrip = null,
                    flightDurationFormatRoundTrip = "",
                    flightIdRoundTrip = "",
                    flightStatusRoundTrip = "",
                    flightSeatRoundTrip = "",
                    flightArrivalDateRoundTrip = "",
                    flightDepartureDateRoundTrip = "",
                    planeTypeRoundTrip = "",
                    priceAdultRoundTrip = null,
                    priceBabyRoundTrip = null,
                    priceChildRoundTrip = null,
                    priceTotalRoundTrip = 0,
                    paymentPriceRoundTrip = null,
                    seatsAvailableRoundTrip = null,
                    terminalRoundTrip = "",
                ),
            )
        }
    }
}
