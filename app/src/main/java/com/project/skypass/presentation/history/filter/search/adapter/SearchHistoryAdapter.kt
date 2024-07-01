package com.project.skypass.presentation.history.filter.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.History
import com.project.skypass.databinding.ItemSearchResultBinding

class SearchHistoryAdapter(private val itemClick: (History) -> Unit) : RecyclerView.Adapter<SearchHistoryAdapter.SearchViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<History>() {
                override fun areItemsTheSame(
                    oldItem: History,
                    newItem: History,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: History,
                    newItem: History,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<History>) {
        dataDiffer.submitList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    override fun onBindViewHolder(
        holder: SearchViewHolder,
        position: Int,
    ) {
        holder.bind(dataDiffer.currentList[position])
    }

    class SearchViewHolder(
        private val binding: ItemSearchResultBinding,
        val itemClick: (History) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History) {
            with(item) {
                binding.tvSearchResult.text = item.bookingCode
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}
