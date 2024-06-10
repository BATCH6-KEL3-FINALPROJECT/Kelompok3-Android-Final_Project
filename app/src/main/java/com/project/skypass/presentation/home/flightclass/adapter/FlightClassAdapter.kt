package com.project.skypass.presentation.home.flightclass.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.R
import com.project.skypass.data.model.SeatClass
import com.project.skypass.databinding.ItemFlightClassBinding

class FlightClassAdapter(private val itemClick: (SeatClass) -> Unit) : RecyclerView.Adapter<FlightClassAdapter.ViewHolder>() {

    private val dataDiffer = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<SeatClass>() {
            override fun areItemsTheSame(
                oldItem: SeatClass,
                newItem: SeatClass
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SeatClass, newItem: SeatClass): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    private var selectedPosition: Int? = null

    fun submitData(data: List<SeatClass>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFlightClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isSelected = position == selectedPosition
        holder.bind(dataDiffer.currentList[position], isSelected)
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    private fun onItemClick(seatClass: SeatClass) {
        val previousSelectedPosition = selectedPosition
        selectedPosition = dataDiffer.currentList.indexOf(seatClass)

        previousSelectedPosition?.let { notifyItemChanged(it) }
        notifyItemChanged(selectedPosition ?: -1)

        itemClick(seatClass)
    }

    class ViewHolder(
        private val binding: ItemFlightClassBinding,
        val itemClick: (SeatClass) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SeatClass, isSelected: Boolean) {
            with(data) {
                binding.tvClass.text = data.classType
                binding.tvPriceClass.text = itemView.context.getString(R.string.price_class, data.price.toString())
                if (isSelected) {
                    binding.ivCheckmark.visibility = View.VISIBLE
                    binding.tvClass.setTextColor(itemView.context.getColor(R.color.white))
                    binding.tvPriceClass.setTextColor(itemView.context.getColor(R.color.white))
                    itemView.setBackgroundResource(R.drawable.bg_selector_seat_class)
                } else {
                    binding.ivCheckmark.visibility = View.GONE
                    binding.tvClass.setTextColor(itemView.context.getColor(R.color.black))
                    binding.tvPriceClass.setTextColor(itemView.context.getColor(R.color.black))
                    itemView.setBackgroundResource(R.drawable.bg_selector_seat_class_none)
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}