package com.teknophase.reduxexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teknophase.redux.applyMiddleware
import com.teknophase.redux.combineReducers
import com.teknophase.redux.createStoreAny
import com.teknophase.reduxexample.redux.reducers.homeReducer
import com.teknophase.reduxexample.state.HomeActions
import com.teknophase.reduxexample.state.HomeEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    environment: HomeEnvironment
) : ViewModel() {
    val store = createStoreAny(
        reducer = combineReducers(homeReducer),
        initialState = environment.initialState,
        enhancer = applyMiddleware(
            environment.getMiddleware(viewModelScope)
        )
    )

    init {
        store.dispatch(HomeActions.LoadPhotos)
    }
}