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
import com.project.skypass.databinding.ItemDestinationFavoriteBinding
import com.project.skypass.utils.formatDatesDestinationFavorite
import com.project.skypass.utils.toIndonesianFormat
import com.project.skypass.utils.toIndonesianFormatDouble

class FavoriteDestinationAdapter(private val itemClick: (Destination) -> Unit): RecyclerView.Adapter<FavoriteDestinationAdapter.DestinationViewHolder>() {

    private val dataDiffer = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<Destination>() {
            override fun areItemsTheSame(
                oldItem: Destination,
                newItem: Destination
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Destination, newItem: Destination): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Destination>) {
        dataDiffer.submitList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemDestinationFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }

    class DestinationViewHolder(
        private val binding: ItemDestinationFavoriteBinding,
        val itemClick: (Destination) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Destination) {
            with(item) {
                binding.ivDestinationFavorite.load(item.imageUrl){
                    crossfade(true)
                }
                binding.tvTitleDestinationFavorite.text = itemView.context.getString(R.string.from_to, item.from, item.to)
                binding.tvAirline.text = item.airline
                //binding.tvDatePromotion.text = "${item.departureDate} - ${item.returnDate}"
                binding.tvPromotion.text = item.discount
                binding.tvDatePromotion.text = formatDatesDestinationFavorite(item.departureDate, item.returnDate)
                binding.tvPrice.text = itemView.context.getString(R.string.idr_rp_fav_des, item.price.toIndonesianFormatDouble())
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}