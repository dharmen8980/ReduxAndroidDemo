package com.teknophase.reduxexample.di

import com.teknophase.reduxexample.network.services.PhotosService
import com.teknophase.reduxexample.network.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

const val AUTH_URL_KEY = "auth"
const val PHOTOS_URL_KEY = "photos"
const val AUTH_CLIENT = "AUTH_CLIENT"
const val PHOTOS_CLIENT = "PHOTOS_CLIENT"

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    private const val AUTH_URL = "https://dummyjson.com/"
    private const val PHOTOS_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Named(AUTH_URL_KEY)
    fun providesAuthURL() : String {
        return AUTH_URL
    }

    @Provides
    @Named(PHOTOS_URL_KEY)
    fun providesPhotosURL() : String {
        return PHOTOS_URL
    }

    @Provides
    @Named(AUTH_CLIENT)
    fun providesAuthRetrofitClient(@Named(AUTH_URL_KEY) baseUrl: String): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named(PHOTOS_CLIENT)
    fun providesPhotosRetrofitClient(@Named(PHOTOS_URL_KEY) baseUrl: String): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesUserService(@Named(AUTH_CLIENT) retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun providesPhotosService(@Named(PHOTOS_CLIENT) retrofit: Retrofit) : PhotosService {
        return retrofit.create(PhotosService::class.java)
    }

}