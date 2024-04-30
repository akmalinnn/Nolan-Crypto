package com.cryptoin.nolancrypto.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cryptoin.nolancrypto.data.repository.UserRepository
import com.cryptoin.nolancrypto.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: UserRepository): ViewModel() {
    fun doLogin(email: String, password: String): LiveData<ResultWrapper<Boolean>> {
        return repository.doLogin(email,password).asLiveData(Dispatchers.IO)
    }
}