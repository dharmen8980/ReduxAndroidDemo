package com.teknophase.reduxexample.redux.reducers

import com.teknophase.redux.Reducer
import com.teknophase.redux.reducerForActionType
import com.teknophase.reduxexample.state.LoginActions
import com.teknophase.reduxexample.state.LoginState

val loginReducer : Reducer<LoginState> = reducerForActionType<LoginState,LoginActions> { state, action ->
    when(action) {
        is LoginActions.Login -> login(state)
        is LoginActions.LoginResult -> loginresult(state,action)
        is LoginActions.UpdateUsername -> updateUsername(state,action)
        is LoginActions.UpdatePassword -> updatePassword(state,action)
    }
}

fun updatePassword(state: LoginState, action: LoginActions.UpdatePassword): LoginState {
    return state.copy(password = action.password)
}

fun updateUsername(state: LoginState, action: LoginActions.UpdateUsername): LoginState {
    return state.copy(username = action.username)
}

fun loginresult(state: LoginState,action: LoginActions.LoginResult): LoginState {
    return state.copy(isLoading = false, isLoggedIn = action.isLoggedIn, user = action.user)
}

fun login(state: LoginState): LoginState {
    if(
        state.isLoggedIn
        || state.isLoading
        || state.username == null
        || state.password == null
        || state.username.isEmpty()
        || state.password.isEmpty()
    ) return state

    return state.copy(isLoading = true)
}


