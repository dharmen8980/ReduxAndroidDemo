package com.teknophase.reduxexample.state

import com.teknophase.redux.Action
import com.teknophase.reduxexample.model.AuthRequestModel

sealed interface LoginActions : Action {
    data class Login(val user: AuthRequestModel) : LoginActions

    data class LoginResult(val isLoggedIn: Boolean) : LoginActions

    data class UpdateUsername(val username: String) : LoginActions

    data class UpdatePassword(val password: String) : LoginActions
}