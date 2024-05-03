package com.cryptoin.nolancrypto.data.repository

import com.cryptoin.nolancrypto.data.datasource.coin.CoinDataSource
import com.cryptoin.nolancrypto.data.mapper.toCoins
import com.cryptoin.nolancrypto.data.model.Coin
import com.cryptoin.nolancrypto.utils.ResultWrapper
import com.cryptoin.nolancrypto.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getCoins(): Flow<ResultWrapper<List<Coin>>>
}

class ProductRepositoryImpl(
    private val dataSource: CoinDataSource,
) : ProductRepository {
    override fun getCoins(): Flow<ResultWrapper<List<Coin>>> {
        return proceedFlow {
            dataSource.getCoins().toCoins()
        }
    }
}
