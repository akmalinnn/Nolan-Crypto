package com.cryptoin.nolancrypto.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.databinding.ItemCategoryBinding
import com.cryptoin.nolancrypto.utils.toDollarFormat

class CoinTopAdapter(private val itemClick: (Coin) -> Unit) :
    RecyclerView.Adapter<CoinTopAdapter.ItemProductViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<Coin>() {
                override fun areItemsTheSame(
                    oldItem: Coin,
                    newItem: Coin,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Coin,
                    newItem: Coin,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(data: List<Coin>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemProductViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemProductViewHolder,
        position: Int,
    ) {
        holder.bindView(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    class ItemProductViewHolder(
        private val binding: ItemCategoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Coin) {
            with(item) {
                binding.tvTopName.text = item.symbol
                binding.tvTopBtcPrice.text = item.currentPrice.toDollarFormat()
                binding.tvTopBtcAth.text = item.percentChange.toString()
            }
        }
    }
}
