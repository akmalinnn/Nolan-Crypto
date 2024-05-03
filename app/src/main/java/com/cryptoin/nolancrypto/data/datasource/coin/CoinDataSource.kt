package com.cryptoin.nolancrypto.data.datasource.coin

import com.cryptoin.nolancrypto.data.source.network.model.coin.CoinResponse

interface CoinDataSource {
    suspend fun getCoins(): List<CoinResponse>
}
