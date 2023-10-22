package com.teknophase.reduxexample.state

import com.teknophase.redux.Middleware
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface LoginEnvironment {
    val initialState: LoginState

    val globalMiddleware: List<Middleware<Any>>

    val dispatcher: CoroutineDispatcher

    fun getMiddleware(coroutineScope: CoroutineScope) : List<Middleware<Any>>
}