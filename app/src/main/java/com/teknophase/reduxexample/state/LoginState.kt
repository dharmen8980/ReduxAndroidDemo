package com.teknophase.reduxexample.state

import com.teknophase.reduxexample.model.User

data class LoginState(
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val user: User? = null
)