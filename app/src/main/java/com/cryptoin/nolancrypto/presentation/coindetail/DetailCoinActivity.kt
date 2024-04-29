package com.cryptoin.nolancrypto.presentation.coindetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.databinding.ActivityDetailCoinBinding
import com.cryptoin.nolancrypto.utils.toDollarFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailCoinActivity : AppCompatActivity() {
    private val binding: ActivityDetailCoinBinding by lazy {
        ActivityDetailCoinBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailCoinViewModel by viewModel {
        parametersOf(intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindProduct(viewModel.coin)

    }


    private fun bindProduct(coin: Coin?) {
        coin?.let { item ->
            binding.tvProductName.text = item.name
            binding.tvProductDesc.text = item.symbol
            binding.tvProductPrice.text = item.currentPrice.toDollarFormat()
        }
    }




    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, coin: Coin) {
            val intent = Intent(context, DetailCoinActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, coin)
            context.startActivity(intent)
        }
    }
}