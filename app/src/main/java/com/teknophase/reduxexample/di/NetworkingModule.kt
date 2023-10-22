package com.teknophase.reduxexample.di

import com.teknophase.reduxexample.network.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

const val AUTH_URL_KEY = "auth"

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    private const val AUTH_URL = "https://dummyjson.com/"

    @Provides
    @Named(AUTH_URL_KEY)
    fun providesAuthURL() : String {
        return AUTH_URL
    }

    @Provides
    fun providesUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }

}