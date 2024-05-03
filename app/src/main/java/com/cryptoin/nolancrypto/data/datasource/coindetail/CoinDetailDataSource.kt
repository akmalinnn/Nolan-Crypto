package com.cryptoin.nolancrypto.data.datasource.coindetail

import com.cryptoin.nolancrypto.data.source.network.model.coindetail.CoinDetailResponse

interface CoinDetailDataSource {
    suspend fun getCoinDetail(id: String): CoinDetailResponse
}
