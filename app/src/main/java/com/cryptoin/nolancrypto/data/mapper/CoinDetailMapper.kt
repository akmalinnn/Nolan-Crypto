package com.cryptoin.nolancrypto.data.mapper

import com.cryptoin.nolancrypto.data.model.CoinDetail
import com.cryptoin.nolancrypto.data.source.network.model.coindetail.CoinDetailResponse

fun CoinDetailResponse.toCoinDetail() =
    CoinDetail(
        id = this.id.orEmpty(),
        symbol = this.symbol.orEmpty(),
        name = this.name.orEmpty(),
        image = this.image?.large.orEmpty(),
        currentPrice = this?.marketData?.currentPrice?.usd ?: 0.0,
        description = this.description?.en.orEmpty(),
        webSlug = this?.webSlug.orEmpty(),
    )
