package com.cryptoin.nolancrypto.di

import android.content.SharedPreferences
import com.cryptoin.nolancrypto.data.datasource.coin.CoinApiDataSource
import com.cryptoin.nolancrypto.data.datasource.coin.CoinDataSource
import com.cryptoin.nolancrypto.data.datasource.user.UserDataSource
import com.cryptoin.nolancrypto.data.datasource.user.UserPreferenceDataSource
import com.cryptoin.nolancrypto.data.repository.ProductRepository
import com.cryptoin.nolancrypto.data.repository.ProductRepositoryImpl
import com.cryptoin.nolancrypto.data.repository.UserRepository
import com.cryptoin.nolancrypto.data.repository.UserRepositoryImpl
import com.cryptoin.nolancrypto.data.source.local.pref.UserPreference
import com.cryptoin.nolancrypto.data.source.local.pref.UserPreferenceImpl
import com.cryptoin.nolancrypto.data.source.network.services.NolanCryptoApiService
import com.cryptoin.nolancrypto.presentation.coindetail.DetailCoinViewModel
import com.cryptoin.nolancrypto.presentation.home.HomeViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


object AppModules {

    private val networkModule = module {
        single<NolanCryptoApiService> { NolanCryptoApiService.invoke() }
    }

    private val localModule = module {
//        single<AppDatabase> { AppDatabase.createInstance(androidContext()) }
//        single<CartDao> { get<AppDatabase>().cartDao() }
        single<SharedPreferences> {
            com.cryptoin.nolancrypto.utils.SharedPreferenceUtils.createPreference(
                androidContext(),
                UserPreferenceImpl.PREF_NAME
            )
        }
        single<UserPreference> { UserPreferenceImpl(get()) }
    }
    private val datasource = module {
        single<CoinDataSource> { CoinApiDataSource(get()) }
        single<UserDataSource> { UserPreferenceDataSource(get()) }
    }

    private val repository = module {
        single<ProductRepository> { ProductRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::HomeViewModel)
        viewModel { params ->
            //assisted injection
            DetailCoinViewModel(
                extras = params.get(),
                productRepository = get()
            )
        }

    }

    val modules = listOf(
        networkModule,
        localModule,
        datasource,
        repository,
        viewModelModule
    )

}