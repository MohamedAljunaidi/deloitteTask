package com.demo.nytimesapp.di

import com.demo.caching.manager.CachingManager
import com.demo.nytimesapp.data.login.LoginRepository
import com.demo.nytimesapp.data.signup.repository.SignUpRepository
import com.demo.nytimesapp.domain.login.repository.ILoginRepository
import com.demo.nytimesapp.domain.signup.repository.ISignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun getSignUpRepository(cachingManager: CachingManager): ISignUpRepository {
        return SignUpRepository(cachingManager)
    }

    @Provides
    @ViewModelScoped
    fun getLoginRepository(cachingManager: CachingManager): ILoginRepository {
        return LoginRepository(cachingManager)
    }


}