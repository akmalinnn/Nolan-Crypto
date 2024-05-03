package com.cryptoin.nolancrypto.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cryptoin.nolancrypto.R
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.databinding.ItemCoinBinding
import com.cryptoin.nolancrypto.utils.toDollarFormat

class CoinListAdapter(private val itemClick: (Coin) -> Unit) :
    RecyclerView.Adapter<CoinListAdapter.ItemProductViewHolder>() {
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
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProductViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(
        holder: ItemProductViewHolder,
        position: Int,
    ) {
        holder.bindView(dataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    class ItemProductViewHolder(
        private val binding: ItemCoinBinding,
        val itemClick: (Coin) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Coin) {
            with(item) {
                binding.tvCoin.text = item.name
                binding.tvSymbol.text = item.symbol
                binding.tvPrice.text = item.currentPrice.toDollarFormat()
                binding.ivCoin.load(this.image) {
                    crossfade(true)
                    error(R.mipmap.ic_launcher)
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}
