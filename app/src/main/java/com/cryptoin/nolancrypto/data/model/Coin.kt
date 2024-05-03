package com.cryptoin.nolancrypto.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    var id: String,
    var symbol: String,
    var name: String,
    var image: String,
    var currentPrice: Double,
) : Parcelable
