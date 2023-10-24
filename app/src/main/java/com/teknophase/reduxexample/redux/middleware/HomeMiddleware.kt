package com.teknophase.reduxexample.redux.middleware

import com.teknophase.redux.Middleware
import com.teknophase.redux.Store
import com.teknophase.redux.createMiddleware
import com.teknophase.reduxexample.network.repositories.PhotosRepository
import com.teknophase.reduxexample.state.HomeActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun createHomeMiddleware(
    repository: PhotosRepository,
    scope: CoroutineScope
) : Middleware<Any> {
    return createMiddleware { store, next, action ->
        next(action)

        when(action) {
            is HomeActions.LoadPhotos -> loadPhotos(store,repository,scope)
        }
    }
}

fun loadPhotos(store: Store<Any>, repository: PhotosRepository, scope: CoroutineScope) {
    scope.launch {
        val photosResponse = repository.getPhotos()
        if(photosResponse.isSuccessful) {
            val photos = photosResponse.body()
            if(photos != null) store.dispatch(HomeActions.PhotosLoadedResult(
                photos = photos,
                error = false
            ))
        } else {
            store.dispatch(HomeActions.PhotosLoadedResult(
                photos = emptyList(),
                error = true
            ))
        }
    }
}
