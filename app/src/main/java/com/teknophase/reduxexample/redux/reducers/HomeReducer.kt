package com.teknophase.reduxexample.redux.reducers

import com.teknophase.redux.Reducer
import com.teknophase.redux.reducerForActionType
import com.teknophase.reduxexample.state.HomeActions
import com.teknophase.reduxexample.state.HomeState

val homeReducer: Reducer<HomeState> =
    reducerForActionType<HomeState, HomeActions> { state, action ->
        when (action) {
            is HomeActions.LoadPhotos -> loadPhotos(state)
            is HomeActions.PhotosLoadedResult -> photosLoadedResult(state, action)
            else -> state
        }
    }

fun photosLoadedResult(state: HomeState, action: HomeActions.PhotosLoadedResult): HomeState {
    return if (action.error) state.copy(isLoading = false, isError = true)
    else state.copy(photos = action.photos, isLoading = false, isError = false)
}

fun loadPhotos(state: HomeState): HomeState {
    return state.copy(isLoading = true)
}