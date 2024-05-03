package com.cryptoin.nolancrypto.presentation.coindetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cryptoin.nolancrypto.data.repository.CoinDetailRepository
import kotlinx.coroutines.Dispatchers

class DetailCoinViewModel(
    private val extras: Bundle?,
    private val coinDetailRepository: CoinDetailRepository,
) : ViewModel() {
    val coinDetail = extras?.getString(DetailCoinActivity.EXTRA_PRODUCT)

    fun getCoinDetails(id: String) = coinDetailRepository.getCoinDetail(id).asLiveData(Dispatchers.IO)

    fun buttonToWeb(
        context: Context,
        url: String,
    ) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coingecko.com/en/coins/$url"))
        context.startActivity(intent)
    }
}
