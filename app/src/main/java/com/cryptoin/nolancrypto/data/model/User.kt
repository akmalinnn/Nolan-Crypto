package com.cryptoin.nolancrypto.data.model

import com.google.firebase.auth.FirebaseUser

data class User(
    val fullName: String,
    val email: String
)
fun FirebaseUser?.toUser() = this?.let {
    User(
        fullName = this.displayName.orEmpty(),
        email = this.email.orEmpty()
    )
}