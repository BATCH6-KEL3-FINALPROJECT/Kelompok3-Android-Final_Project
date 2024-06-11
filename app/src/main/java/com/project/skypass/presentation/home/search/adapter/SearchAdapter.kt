package com.project.skypass.presentation.home.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.skypass.data.model.Search
import com.project.skypass.databinding.ItemSearchResultBinding

class SearchAdapter(private val itemClick: (Search) -> Unit): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val dataDiffer = AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(
                oldItem: Search,
                newItem: Search
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Search>) {
        dataDiffer.submitList(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int {
        return dataDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }

    class SearchViewHolder(
        private val binding: ItemSearchResultBinding,
        val itemClick: (Search) -> Unit
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Search){
            with(item){
                binding.tvSearchResult.text = item.city
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}