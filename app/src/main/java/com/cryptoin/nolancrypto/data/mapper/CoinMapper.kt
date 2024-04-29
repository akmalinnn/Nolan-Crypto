package com.cryptoin.nolancrypto.data.mapper

import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.data.source.network.model.coin.CoinResponse



fun CoinResponse.toCoin() =
    Coin(
        id = this.id.orEmpty(),
        symbol = this.symbol.orEmpty(),
        name = this.name.orEmpty(),
        image = this.image.orEmpty(),
        currentPrice = this.currentPrice?: 0.0
    )

fun Collection<CoinResponse>.toCoins() = this.map {
    it.toCoin()
}