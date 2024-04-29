package com.cryptoin.nolancrypto.data.source.network.model.coindetail

import com.google.gson.annotations.SerializedName

data class CoinDetailResponse (
    @SerializedName("description")
    var description: Description? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("symbol")
    var symbol: String? = null,
    @SerializedName("image")
    var image: ImageCoin? = null,
    @SerializedName("current_price")
    var currentPrice: String?,

)

data class ImageCoin(
    @SerializedName("large")
    var large: String? = null
)

data class Description(

    @SerializedName("en")
    var en: String? = null
)
