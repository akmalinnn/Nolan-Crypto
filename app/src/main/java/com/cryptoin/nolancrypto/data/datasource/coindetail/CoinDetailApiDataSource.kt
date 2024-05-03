package com.cryptoin.nolancrypto.data.datasource.coindetail

import com.cryptoin.nolancrypto.data.source.network.model.coindetail.CoinDetailResponse
import com.cryptoin.nolancrypto.data.source.network.services.NolanCryptoApiService

class CoinDetailApiDataSource(private val service: NolanCryptoApiService) : CoinDetailDataSource {
    override suspend fun getCoinDetail(id: String): CoinDetailResponse {
        return service.getCoinDetail(id = id)
    }
}
