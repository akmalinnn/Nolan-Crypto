package com.cryptoin.nolancrypto.data.repository

import com.cryptoin.nolancrypto.data.datasource.coindetail.CoinDetailDataSource
import com.cryptoin.nolancrypto.data.mapper.toCoinDetail
import com.cryptoin.nolancrypto.data.model.CoinDetail
import com.cryptoin.nolancrypto.utils.ResultWrapper
import com.cryptoin.nolancrypto.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface CoinDetailRepository {
    fun getCoinDetail(id: String): Flow<ResultWrapper<CoinDetail>>
}

class CoinDetailRepositoryImpl(private val dataSource: CoinDetailDataSource) : CoinDetailRepository {
    override fun getCoinDetail(id: String): Flow<ResultWrapper<CoinDetail>> {
        return proceedFlow {
            dataSource.getCoinDetail(id).toCoinDetail()
        }
    }
}
