package com.teknophase.redux

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface Store<State> {
    val state: State
        get() = stateFlow.value

    var dispatch: Dispatch

    val stateFlow: StateFlow<State>
}

interface Action

typealias Dispatch = (action: Action) -> Unit

typealias StoreEnhancer<State> = (StoreCreator<State>) -> StoreCreator<State>

typealias StoreCreator<State> = (reducer: Reducer<State>, initialState: State, enhancer: Any?) -> Store<State>

fun <State> createStore(
    reducer: Reducer<State>,
    initialState: State,
    enhancer: StoreEnhancer<State>? = null
): Store<State> {
    // If there is an enhancer, we should apply it to the store.
    if (enhancer != null) {
        return enhancer { originalReducer, originalState, _ ->
            createStore(originalReducer, originalState)
        }(reducer, initialState, null)
    }

    // An internal state flow that manages the current state of the Store.
    val mutableStateFlow = MutableStateFlow(initialState)

    // A state flow that publishes the current state of the Store.
    val stateFlow = mutableStateFlow.asStateFlow()

    // Dispatches an action to the [Store] in order to update its [State].
    fun dispatch(action: Action) {
        mutableStateFlow.update { reducer(it, action) }
    }

    return object : Store<State> {
        override var dispatch: Dispatch = ::dispatch
        override val stateFlow: StateFlow<State> = stateFlow
    }
}

fun <State> createStoreAny(
    reducer: Reducer<State>,
    initialState: State,
    enhancer: StoreEnhancer<Any>? = null
): Store<State> {
    val enhancerTyped = enhancer as StoreEnhancer<State>
    return createStore(reducer, initialState, enhancerTyped)
}

fun <State> combineReducers(vararg reducers: Reducer<State>): Reducer<State> = { state, action ->
    reducers.fold(state) { s, reducer -> reducer(s, action) }
}
