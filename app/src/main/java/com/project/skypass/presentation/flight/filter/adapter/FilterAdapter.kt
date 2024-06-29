package com.project.skypass.presentation.flight.filter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.R
import com.project.skypass.data.model.FilterFlight
import com.project.skypass.databinding.ItemFlightClassBinding

class FilterAdapter(
    private val itemClick: (FilterFlight) -> Unit,
) : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    private val data = mutableListOf<FilterFlight>()

    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<FilterFlight>() {
                override fun areItemsTheSame(
                    oldItem: FilterFlight,
                    newItem: FilterFlight,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: FilterFlight,
                    newItem: FilterFlight,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    private var selectedPosition: Int? = null

    private fun onItemClick(seatClass: FilterFlight) {
        val previousSelectedPosition = selectedPosition
        selectedPosition = dataDiffer.currentList.indexOf(seatClass)

        previousSelectedPosition?.let { notifyItemChanged(it) }
        notifyItemChanged(selectedPosition ?: -1)

        itemClick(seatClass)
    }

    fun submitData(data: List<FilterFlight>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FilterViewHolder {
        val binding =
            ItemFlightClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(binding, ::onItemClick)
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    override fun onBindViewHolder(
        holder: FilterViewHolder,
        position: Int,
    ) {
        val isSelected = position == selectedPosition
        holder.bind(dataDiffer.currentList[position], isSelected)
    }

    class FilterViewHolder(
        private val binding: ItemFlightClassBinding,
        val itemClick: (FilterFlight) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: FilterFlight,
            isSelected: Boolean,
        ) {
            with(item) {
                binding.tvClass.text = item.criteria
                if (isSelected) {
                    binding.ivCheckmark.visibility = View.VISIBLE
                    binding.tvClass.setTextColor(itemView.context.getColor(R.color.white))
                    itemView.setBackgroundResource(R.drawable.bg_selector_seat_class)
                } else {
                    binding.ivCheckmark.visibility = View.GONE
                    binding.tvClass.setTextColor(itemView.context.getColor(R.color.black))
                    itemView.setBackgroundResource(R.drawable.bg_selector_seat_class_none)
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}

interface ViewHolderBinder<T> {
    fun bind(item: T)
}
