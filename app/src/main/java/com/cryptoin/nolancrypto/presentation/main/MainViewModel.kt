package com.cryptoin.nolancrypto.presentation.main

import androidx.lifecycle.ViewModel
import com.cryptoin.nolancrypto.data.repository.UserRepository

class MainViewModel(private val repository: UserRepository) : ViewModel() {
//    constructor() : this(UserRepositoryImpl(FirebaseAuthDataSource(FirebaseServiceImpl())))

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
}
