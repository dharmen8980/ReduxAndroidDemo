package com.teknophase.reduxexample.di

import com.teknophase.redux.Middleware
import com.teknophase.reduxexample.model.AuthRequestModel
import com.teknophase.reduxexample.model.ListItemModel
import com.teknophase.reduxexample.model.User
import com.teknophase.reduxexample.network.repositories.PhotosRepository
import com.teknophase.reduxexample.network.repositories.UserRepository
import com.teknophase.reduxexample.network.services.PhotosService
import com.teknophase.reduxexample.network.services.UserService
import com.teknophase.reduxexample.redux.middleware.createAuthMiddleware
import com.teknophase.reduxexample.redux.middleware.createHomeMiddleware
import com.teknophase.reduxexample.state.HomeEnvironment
import com.teknophase.reduxexample.state.HomeState
import com.teknophase.reduxexample.state.LoginEnvironment
import com.teknophase.reduxexample.state.LoginState
import com.teknophase.reduxexample.viewmodel.HomeViewModel
import com.teknophase.reduxexample.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun providesHomeEnvironment(photosRepository: PhotosRepository): HomeEnvironment {
        return object : HomeEnvironment {
            override val initialState: HomeState
                get() = HomeState()
            override val globalMiddleware: List<Middleware<Any>>
                get() = emptyList()
            override val dispatcher: CoroutineDispatcher
                get() = Dispatchers.IO

            override fun getMiddleware(coroutineScope: CoroutineScope): List<Middleware<Any>> {
                return globalMiddleware + createHomeMiddleware(photosRepository, coroutineScope)
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

    @Provides
    fun providesPhotosRepository(photosService: PhotosService) : PhotosRepository {
        return object : PhotosRepository {
            override suspend fun getPhotos(): Response<List<ListItemModel>> {
                return photosService.getPhotos()
            }
        }
    }

    @Provides
    @Singleton
    fun providesLoginViewModel(loginEnvironment: LoginEnvironment) : LoginViewModel {
        return LoginViewModel(loginEnvironment)
    }

    @Provides
    @Singleton
    fun providesHomeViewModel(homeEnvironment: HomeEnvironment) : HomeViewModel {
        return HomeViewModel(homeEnvironment)
    }

}