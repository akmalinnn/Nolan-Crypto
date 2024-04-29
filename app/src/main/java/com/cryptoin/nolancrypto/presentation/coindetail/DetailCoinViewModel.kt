package com.cryptoin.nolancrypto.presentation.coindetail

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers


class DetailCoinViewModel(
    private val extras: Bundle? , private val productRepository: ProductRepository
) : ViewModel() {

   val coin = extras?.getParcelable<Coin>(DetailCoinActivity.EXTRA_PRODUCT)

    fun getCoinDetails(id: String) =
        productRepository.getCoinDetails(id).asLiveData(Dispatchers.IO)

}