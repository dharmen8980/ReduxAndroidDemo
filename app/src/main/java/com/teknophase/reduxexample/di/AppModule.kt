package com.teknophase.reduxexample.di

import com.teknophase.redux.Middleware
import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.model.User
import com.teknophase.reduxexample.network.repositories.UserRepository
import com.teknophase.reduxexample.network.services.UserService
import com.teknophase.reduxexample.redux.middleware.createAuthMiddleware
import com.teknophase.reduxexample.state.LoginEnvironment
import com.teknophase.reduxexample.state.LoginState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesRetrofitClient(@Named(AUTH_URL_KEY) baseUrl: String): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesLoginEnvironment(userRepository: UserRepository): LoginEnvironment {
        return object : LoginEnvironment {
            override val initialState: LoginState
                get() = LoginState()
            override val globalMiddleware: List<Middleware<Any>>
                get() = emptyList()
            override val dispatcher: CoroutineDispatcher
                get() = Dispatchers.IO

            override fun getMiddleware(coroutineScope: CoroutineScope): List<Middleware<Any>> {
                return globalMiddleware + createAuthMiddleware(userRepository, coroutineScope)
            }

        }
    }

    @Provides
    fun providesUserRepository(userService: UserService) : UserRepository {
        return object : UserRepository {
            override suspend fun login(user: AuthRequestModel): Response<User> {
                return userService.login(user)
            }

        }
    }

}