package com.app.androidcompose.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import leegroup.module.data.repositories.GitUserDetailRepositoryImpl
import leegroup.module.data.repositories.GitUserRepositoryImpl
import leegroup.module.data.repositories.UserRepositoryImpl
import leegroup.module.domain.repositories.GitUserDetailRepository
import leegroup.module.domain.repositories.GitUserRepository
import leegroup.module.domain.repositories.UserRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindGitUserRepository(repository: GitUserRepositoryImpl): GitUserRepository

    @Binds
    fun bindGitUserDetailRepository(repository: GitUserDetailRepositoryImpl): GitUserDetailRepository
}