package com.teknophase.reduxexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknophase.redux.applyMiddleware
import com.teknophase.redux.combineReducers
import com.teknophase.redux.createStoreAny
import com.teknophase.reduxexample.redux.middleware.createAuthMiddleware
import com.teknophase.reduxexample.redux.reducers.loginReducer
import com.teknophase.reduxexample.state.LoginEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    val loginEnvironment: LoginEnvironment
) : ViewModel() {
    val loginStore = createStoreAny(
        initialState = loginEnvironment.initialState,
        enhancer = applyMiddleware(
            loginEnvironment.getMiddleware(viewModelScope)
        ),
        reducer = combineReducers(loginReducer)
    )
}