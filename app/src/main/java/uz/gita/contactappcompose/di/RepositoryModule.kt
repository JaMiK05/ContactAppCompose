package uz.gita.contactappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappcompose.domain.repository.crud.AppRepository
import uz.gita.contactappcompose.domain.repository.crud.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}