package com.app.androidcompose.di

import com.app.androidcompose.support.util.DispatchersProvider
import com.app.androidcompose.support.util.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDispatchersProvider(dispatcher: DispatchersProviderImpl): DispatchersProvider = dispatcher
}
