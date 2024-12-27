package com.app.androidcompose.di

import android.content.Context
import com.app.androidcompose.support.util.DispatchersProvider
import com.app.androidcompose.support.util.DispatchersProviderImpl
import com.lyft.kronos.AndroidClockFactory
import com.lyft.kronos.KronosClock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideDispatchersProvider(dispatcher: DispatchersProviderImpl): DispatchersProvider = dispatcher

    @Provides
    fun provideKronosClock(@ApplicationContext context: Context): KronosClock {
        return AndroidClockFactory.createKronosClock(context)
    }
}
