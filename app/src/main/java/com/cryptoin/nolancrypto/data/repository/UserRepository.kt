package com.cryptoin.nolancrypto.data.repository

import com.cryptoin.nolancrypto.data.datasource.user.UserDataSource


interface UserRepository {
    fun isUsingDarkMode(): Boolean
    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository {
    
    override fun isUsingDarkMode(): Boolean {
        return dataSource.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        dataSource.setUsingDarkMode(isUsingDarkMode)
    }
}