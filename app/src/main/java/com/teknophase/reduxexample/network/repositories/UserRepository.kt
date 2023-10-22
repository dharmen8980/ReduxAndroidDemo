package com.teknophase.reduxexample.network.repositories

import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.model.User
import retrofit2.Response

interface UserRepository {
    suspend fun login(
        user: AuthRequestModel
    ) : Response<User>
}