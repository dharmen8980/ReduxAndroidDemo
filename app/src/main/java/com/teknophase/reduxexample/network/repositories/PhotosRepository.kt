package com.teknophase.reduxexample.network.repositories

import com.teknophase.reduxexample.model.ListItemModel
import retrofit2.Response

interface PhotosRepository {
    suspend fun getPhotos() : Response<List<ListItemModel>>
}