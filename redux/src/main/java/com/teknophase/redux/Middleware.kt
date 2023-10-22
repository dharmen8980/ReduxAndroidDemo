package com.teknophase.redux

typealias Middleware<State> = (store: Store<State>) -> (next: Dispatch) -> Dispatch

interface GlobalMiddlewareProvider<State> {
    fun get(): List<Middleware<Any>>
}

fun <State> createMiddleware(
    middleware: (Store<State>, next: Dispatch, action: Action) -> Any
): Middleware<State> = { store ->
    { next ->
        { action: Action ->
            middleware(store, next, action)
        }
    }
}

fun <State> applyMiddleware(middlewares: List<Middleware<State>>): StoreEnhancer<State> {
    return { storeCreator ->
        { reducer, initialState, enhancer ->
            val store = storeCreator(reducer, initialState, enhancer)
            val originalDispatch = store.dispatch

            store.dispatch = {
                throw IllegalStateException(
                    "Can't dispatch to a store while its middleware is being created."
                )
            }

            val chain = middlewares.map { middleware -> middleware(store) }
            val newDispatch = chain.foldRight(originalDispatch) { next, prev -> next(prev) }
            store.dispatch = newDispatch
            store
        }
    }
}

fun <State> applyMiddleware(vararg middlewares: Middleware<State>): StoreEnhancer<State> {
    return applyMiddleware(middlewares.asList())
}
