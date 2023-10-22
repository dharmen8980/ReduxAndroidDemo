package com.teknophase.redux

typealias Reducer<State> = ReducerTyped<State,Action>

typealias ReducerTyped<State,Action> = (state: State,Action) -> State

inline fun <State, reified Action> reducerForActionType(
    crossinline reducer: ReducerTyped<State, Action>
): Reducer<State> = { state, action ->
    when (action) {
        is Action -> reducer(state, action)
        else -> state
    }
}