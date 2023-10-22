package com.teknophase.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take

suspend fun <State> Store<State>.nextState(scope: CoroutineScope, action: Action): State {
    val intermediateFlow = stateFlow.stateIn(scope, SharingStarted.Eagerly, state)
        .drop(1)
        .take(1)

    dispatch(action)

    return intermediateFlow.single()
}

suspend fun <State> Store<State>.nextState(scope: CoroutineScope, action: Action, predicate: suspend (State) -> Boolean): State {
    val intermediateFlow = stateFlow.stateIn(scope, SharingStarted.Eagerly, state)
        .drop(1)
        .filter(predicate)
        .take(1)

    dispatch(action)

    return intermediateFlow.single()
}
