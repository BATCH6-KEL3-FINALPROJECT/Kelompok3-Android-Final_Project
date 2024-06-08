package com.project.skypass.presentation.home.flightclass.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.R
import com.project.skypass.data.model.SeatClass
import com.project.skypass.databinding.ItemFlightClassBinding

class FlightClassAdapter(private val itemClick: (SeatClass) -> Unit): RecyclerView.Adapter<FlightClassAdapter.ViewHolder>() {

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

    fun submitData(data: List<SeatClass>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemFlightClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    class ViewHolder(
        private val binding: ItemFlightClassBinding,
        val itemClick: (SeatClass) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SeatClass) {
            with(data){
                binding.tvClass.text = data.classType
                binding.tvPriceClass.text = itemView.context.getString(R.string.price_class, data.price.toString())
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}