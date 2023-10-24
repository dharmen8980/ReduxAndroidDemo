package com.teknophase.reduxexample.network.services

import com.teknophase.reduxexample.model.ListItemModel
import retrofit2.Response
import retrofit2.http.GET

interface PhotosService {
    @GET("photos")
    suspend fun getPhotos() : Response<List<ListItemModel>>
}