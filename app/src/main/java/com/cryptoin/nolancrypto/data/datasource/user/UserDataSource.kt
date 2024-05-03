package com.cryptoin.nolancrypto.data.datasource.user

import com.cryptoin.nolancrypto.data.source.local.pref.UserPreference

interface UserDataSource {
    fun isUsingDarkMode(): Boolean

    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}

class UserPreferenceDataSource(private val userPreference: UserPreference) : UserDataSource {
    override fun isUsingDarkMode(): Boolean {
        return userPreference.isUsingDarkMode()
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        userPreference.setUsingDarkMode(isUsingDarkMode)
    }
}
