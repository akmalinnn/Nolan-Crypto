package com.cryptoin.nolancrypto.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cryptoin.nolancrypto.data.repository.ProductRepository
import com.cryptoin.nolancrypto.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers


class HomeViewModel(
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    fun getCoins() =
        productRepository.getCoins().asLiveData(Dispatchers.IO)


}