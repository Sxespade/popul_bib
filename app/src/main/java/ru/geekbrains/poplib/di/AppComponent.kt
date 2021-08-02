package ru.geekbrains.poplib.di

import dagger.Component
import ru.geekbrains.poplib.di.module.ApiModule
import ru.geekbrains.poplib.di.module.AppModule
import ru.geekbrains.poplib.di.module.CiceroneModule
import ru.geekbrains.poplib.di.module.DatabaseModule
import ru.geekbrains.poplib.di.module.ImageModule
import ru.geekbrains.poplib.di.user.UserSubcomponent
import ru.geekbrains.poplib.mvp.presenter.MainPresenter
import ru.geekbrains.poplib.ui.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun userSubcomponent() : UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}