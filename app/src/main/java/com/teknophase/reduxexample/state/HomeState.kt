package com.teknophase.reduxexample.state

import com.teknophase.reduxexample.model.ListItemModel

data class HomeState(
    val photos: List<ListItemModel> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)