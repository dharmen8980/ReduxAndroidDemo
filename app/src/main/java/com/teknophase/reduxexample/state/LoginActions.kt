package com.teknophase.reduxexample.state

import com.teknophase.redux.Action
import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.model.User

sealed interface LoginActions : Action {
    data class Login(val user: AuthRequestModel) : LoginActions

    data class LoginResult(val isLoggedIn: Boolean,val user: User) : LoginActions

    data class UpdateUsername(val username: String) : LoginActions

    data class UpdatePassword(val password: String) : LoginActions
}