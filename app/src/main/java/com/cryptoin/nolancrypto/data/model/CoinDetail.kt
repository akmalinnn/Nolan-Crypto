package com.cryptoin.nolancrypto.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinDetail(
    var id: String,
    var symbol: String,
    var name: String,
    var image: String,
    var currentPrice: Double,
    var description: String,
    var webSlug: String,
) : Parcelable
