package com.cryptoin.nolancrypto.presentation.coindetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.cryptoin.nolancrypto.R
import com.cryptoin.nolancrypto.data.model.CoinDetail
import com.cryptoin.nolancrypto.databinding.ActivityDetailCoinBinding
import com.cryptoin.nolancrypto.databinding.LayoutDetailItemCoinBinding
import com.cryptoin.nolancrypto.utils.proceedWhen
import com.cryptoin.nolancrypto.utils.toDollarFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailCoinActivity : AppCompatActivity() {
    private val binding: ActivityDetailCoinBinding by lazy {
        ActivityDetailCoinBinding.inflate(layoutInflater)
    }

    private val itemCoinBinding: LayoutDetailItemCoinBinding by lazy {
        LayoutDetailItemCoinBinding.bind(binding.layoutDetailItemCoin.root)
    }

    private val viewModel: DetailCoinViewModel by viewModel {
        parametersOf(intent.extras)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickAction()
        viewModel.coinDetail?.let { getCoinDetails(it) }
    }

    private fun getCoinDetails(id: String) {
        viewModel.getCoinDetails(id).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutDetailItemCoin.root.isVisible = true
                    binding.clBtnWeb.btnToWeb.isEnabled = true
                    it.payload?.let { data ->
                        bindProduct(data)
                        setBtnWeb(data.webSlug)
                    }
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.layoutDetailItemCoin.root.isVisible = false
                    binding.clBtnWeb.btnToWeb.isEnabled = false
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = it.exception?.message.orEmpty()
                    binding.layoutDetailItemCoin.root.isVisible = false
                    binding.clBtnWeb.btnToWeb.isEnabled = false
                },
                doOnEmpty = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text =
                        getString(R.string.text_data_not_available)
                    binding.layoutDetailItemCoin.root.isVisible = false
                    binding.clBtnWeb.btnToWeb.isEnabled = false
                },
            )
        }
    }

    private fun setClickAction() {
        binding.ibBackToHome.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setBtnWeb(webSlug: String) {
        binding.clBtnWeb.btnToWeb.setOnClickListener {
            goToWeb(webSlug)
        }
    }

    private fun goToWeb(webSlug: String) {
        viewModel.buttonToWeb(this, webSlug)
    }

    private fun bindProduct(coinDetail: CoinDetail?) {
        coinDetail?.let { item ->
            binding.layoutDetailItemCoin.ivLogoCoin.load(item.image) {
                crossfade(true)
            }
            itemCoinBinding.tvProductName.text = item.name
            binding.tvProductDesc.text = item.symbol
            itemCoinBinding.tvCurrentPrice.text = item.currentPrice.toDollarFormat()
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"

        fun startActivity(
            context: Context,
            id: String,
        ) {
            val intent = Intent(context, DetailCoinActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, id)
            context.startActivity(intent)
        }
    }
}
