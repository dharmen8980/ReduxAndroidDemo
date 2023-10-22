package com.teknophase.reduxexample.network.services

import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("auth/login")
    suspend fun login(@Body user: AuthRequestModel) : Response<User>
}