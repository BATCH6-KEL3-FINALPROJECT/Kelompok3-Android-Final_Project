package com.project.skypass.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.project.skypass.data.model.BannerHome
import com.project.skypass.databinding.ItemBannerHomeBinding
import com.project.skypass.presentation.home.detailBanner.DetailBannerActivity

class PromotionAdapter :
    ListAdapter<BannerHome, PromotionAdapter.PromotionViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PromotionViewHolder {
        val binding =
            ItemBannerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PromotionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PromotionViewHolder,
        position: Int,
    ) {
//        val actualPosition = position % currentList.size
//        val promotion = currentList[actualPosition]

        val promotion = getItem(position)

        if (promotion != null) {
            holder.bind(promotion)
        }
    }

    class PromotionViewHolder(private val binding: ItemBannerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BannerHome) {
            binding.tvCityBanner.text = item.city
            binding.tvTitleBannerHome.text = item.title
            binding.tvDestinationPriceLabel.text = item.description
            binding.ivBackground.load(item.imageUrl) {
                crossfade(true)
            }
            itemView.setOnClickListener {
                sendToBannerDetail(item)
            }
        }

        private fun sendToBannerDetail(data: BannerHome) {
            DetailBannerActivity.startActivity(binding.root.context, data)
        }
    }

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<BannerHome>() {
                override fun areItemsTheSame(
                    oldItem: BannerHome,
                    newItem: BannerHome,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: BannerHome,
                    newItem: BannerHome,
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}
