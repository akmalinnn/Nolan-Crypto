package com.cryptoin.nolancrypto.di

import android.content.SharedPreferences
import com.cryptoin.nolancrypto.data.datasource.coin.CoinApiDataSource
import com.cryptoin.nolancrypto.data.datasource.coin.CoinDataSource
import com.cryptoin.nolancrypto.data.datasource.firebaseauth.AuthDataSource
import com.cryptoin.nolancrypto.data.datasource.firebaseauth.FirebaseAuthDataSource
import com.cryptoin.nolancrypto.data.datasource.user.UserDataSource
import com.cryptoin.nolancrypto.data.datasource.user.UserPreferenceDataSource
import com.cryptoin.nolancrypto.data.repository.ProductRepository
import com.cryptoin.nolancrypto.data.repository.ProductRepositoryImpl
import com.cryptoin.nolancrypto.data.repository.UserRepository
import com.cryptoin.nolancrypto.data.repository.UserRepositoryImpl
import com.cryptoin.nolancrypto.data.source.local.pref.UserPreference
import com.cryptoin.nolancrypto.data.source.local.pref.UserPreferenceImpl
import com.cryptoin.nolancrypto.data.source.network.services.NolanCryptoApiService
import com.cryptoin.nolancrypto.data.source.network.services.firebase.FirebaseService
import com.cryptoin.nolancrypto.data.source.network.services.firebase.FirebaseServiceImpl
import com.cryptoin.nolancrypto.presentation.coindetail.DetailCoinViewModel
import com.cryptoin.nolancrypto.presentation.home.HomeViewModel
import com.cryptoin.nolancrypto.presentation.login.LoginViewModel
import com.cryptoin.nolancrypto.presentation.main.MainViewModel
import com.cryptoin.nolancrypto.presentation.profile.ProfileViewModel
import com.cryptoin.nolancrypto.presentation.register.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.scope.get
import org.koin.dsl.module


object AppModules {

    private val networkModule = module {
        single<NolanCryptoApiService> { NolanCryptoApiService.invoke() }
    }

    val firebaseModule = module {
        single<FirebaseAuth> { FirebaseAuth.getInstance() }
        single<FirebaseService> { FirebaseServiceImpl() }
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
        single<AuthDataSource> { FirebaseAuthDataSource(get()) }
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
        viewModel { MainViewModel(get()) }
        viewModelOf(::ProfileViewModel)
        viewModelOf(::LoginViewModel)
    }

    val modules = listOf(
        networkModule,
        firebaseModule,
        localModule,
        datasource,
        repository,
        viewModelModule
    )

}