package com.project.skypass.presentation.flight.filter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Filter
import com.project.skypass.databinding.ItemFilterBinding

class FilterAdapter(private val itemClick: (Filter) -> Unit) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {
    private val data = mutableListOf<Filter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding =
            ItemFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return FilterViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int, ) {
        holder.bind(data[position])
    }

    fun submitData(items: List<Filter>) {
        items.forEach { newItem ->
            if (!data.contains(newItem)) {
                data.add(newItem)
            }
        }
    }

    class FilterViewHolder(
        private val binding: ItemFilterBinding,
        val itemClick: (Filter) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Filter> {

        override fun bind(item: Filter) {
            with(item) {
                binding.tvFilterCondition1.text = item.filter_condition_1
                binding.tvFilterCondition2.text = item.filter_condition_2
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}

interface ViewHolderBinder<T> {
    fun bind(item: T)
}