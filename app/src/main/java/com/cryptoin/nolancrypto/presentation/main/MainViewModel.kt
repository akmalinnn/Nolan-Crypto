package com.cryptoin.nolancrypto.presentation.main

import androidx.lifecycle.ViewModel
import com.cryptoin.nolancrypto.data.datasource.firebaseauth.FirebaseAuthDataSource
import com.cryptoin.nolancrypto.data.repository.UserRepository
import com.cryptoin.nolancrypto.data.repository.UserRepositoryImpl
import com.cryptoin.nolancrypto.data.source.network.services.firebase.FirebaseServiceImpl

class MainViewModel(private val repository: UserRepository) : ViewModel() {
//    constructor() : this(UserRepositoryImpl(FirebaseAuthDataSource(FirebaseServiceImpl())))

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
}