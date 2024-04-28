package com.cryptoin.nolancrypto.data.datasource.coin


import com.cryptoin.nolancrypto.data.source.network.model.coindetail.CoinDetailResponse
import com.cryptoin.nolancrypto.data.source.network.model.coin.CoinResponse
import com.cryptoin.nolancrypto.data.source.network.services.NolanCryptoApiService

class CoinApiDataSource(
    private val service: NolanCryptoApiService
) : CoinDataSource {
    override suspend fun getCoins() : List<CoinResponse>  {
        return service.getCoins()
    }
    override suspend fun getCoinDetail(coinId: String): CoinDetailResponse {
        return service.getCryptoDetail(coinId)
    }


}


