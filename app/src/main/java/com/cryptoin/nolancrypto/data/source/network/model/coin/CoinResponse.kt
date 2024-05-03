package com.cryptoin.nolancrypto.data.source.network.model.coin

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("symbol")
    var symbol: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("current_price")
    var currentPrice: Double?,
    @SerializedName("price_change_percentage_24h")
    var percentChange: Double?,
)
