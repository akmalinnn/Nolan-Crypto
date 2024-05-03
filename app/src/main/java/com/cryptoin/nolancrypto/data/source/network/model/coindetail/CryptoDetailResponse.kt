package com.cryptoin.nolancrypto.data.source.network.model.coindetail

import com.google.gson.annotations.SerializedName

data class CoinDetailResponse(
    @SerializedName("description")
    var description: Description? = Description(),
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("symbol")
    var symbol: String? = null,
    @SerializedName("image")
    var image: ImageCoin? = ImageCoin(),
    @SerializedName("current_price")
    var currentPrice: String?,
    @SerializedName("market_data")
    var marketData: MarketData? = MarketData(),
    @SerializedName("web_slug")
    var webSlug: String? = null,
)

data class ImageCoin(
    @SerializedName("large")
    var large: String? = null,
)

data class Description(
    @SerializedName("en")
    var en: String? = null,
)

data class CurrentPrice(
    @SerializedName("usd")
    var usd: Double? = null,
)

data class MarketData(
    @SerializedName("current_price")
    var currentPrice: CurrentPrice? = CurrentPrice(),
)
