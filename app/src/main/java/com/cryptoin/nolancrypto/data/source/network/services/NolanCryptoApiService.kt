package com.cryptoin.nolancrypto.data.source.network.services

import com.cryptoin.nolancrypto.BuildConfig
import com.cryptoin.nolancrypto.data.source.network.model.coindetail.CoinDetailResponse
import com.cryptoin.nolancrypto.data.source.network.model.coin.CoinResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface NolanCryptoApiService {

    @Headers("accept: application/json", "x-cg-demo-api-key: CG-S4m7y1shFUUuFzSKrNouRhP2")
    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=30&sparkline=false")
    suspend fun getCoins(): List<CoinResponse>

    @GET("coins/{id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCryptoDetail(@Path("id") id : String): CoinDetailResponse

    companion object {
        @JvmStatic
        operator fun invoke(): NolanCryptoApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(NolanCryptoApiService::class.java)
        }
    }
}