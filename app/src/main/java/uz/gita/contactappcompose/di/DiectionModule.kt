package uz.gita.contactappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.contactappcompose.ui.screen.addcontact.direction.AddScreeenDirectionImpl
import uz.gita.contactappcompose.ui.screen.addcontact.direction.AddScreenDirection
import uz.gita.contactappcompose.ui.screen.home.direction.HomeScreenDirectionImpl
import uz.gita.contactappcompose.ui.screen.home.direction.HomeScreenDirection
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DiectionModule {

    @[Binds]
    fun bindHomeScreenToAddScreen(impl: HomeScreenDirectionImpl): HomeScreenDirection

    @[Binds]
    fun bindAddScreenToHomeScreen(impl: AddScreeenDirectionImpl): AddScreenDirection

}