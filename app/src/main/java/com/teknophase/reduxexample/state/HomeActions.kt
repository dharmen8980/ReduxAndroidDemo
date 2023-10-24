package com.teknophase.reduxexample.state

import com.teknophase.redux.Action
import com.teknophase.reduxexample.model.ListItemModel

interface HomeActions : Action {
    object LoadPhotos : HomeActions

    data class PhotosLoadedResult(val photos: List<ListItemModel>,val error: Boolean) : HomeActions
}