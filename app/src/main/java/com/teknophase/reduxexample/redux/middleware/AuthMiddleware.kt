package com.teknophase.reduxexample.redux.middleware

import com.teknophase.redux.Middleware
import com.teknophase.redux.Store
import com.teknophase.redux.createMiddleware
import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.network.repositories.UserRepository
import com.teknophase.reduxexample.state.LoginActions
import com.teknophase.reduxexample.state.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun createAuthMiddleware(
    repository: UserRepository,
    scope: CoroutineScope
) : Middleware<Any> {
    return createMiddleware { store, next, action ->
        next(action)

        when(action) {
            is LoginActions.Login -> login(store,repository,scope,action.user)
        }
    }
}

fun login(
    store: Store<Any>,
    repository: UserRepository,
    scope: CoroutineScope,
    user: AuthRequestModel
) {
    scope.launch {
        val response = repository.login(user)
        response.let {
            if(response.isSuccessful) {
                val user = response.body()
                if(user!=null){
                    store.dispatch(LoginActions.LoginResult(true,user))
                }
            }
        }
    }
}