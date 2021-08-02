package ru.geekbrains.poplib.ui

import android.app.Application
import ru.geekbrains.poplib.di.AppComponent
import ru.geekbrains.poplib.di.DaggerAppComponent
import ru.geekbrains.poplib.di.module.AppModule
import ru.geekbrains.poplib.di.repository.RepositorySubcomponent
import ru.geekbrains.poplib.di.user.UserSubcomponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun releaseUserSubcomponent() {
        userSubcomponent = null
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    fun releaseRepositorySubcomponent() {
        repositorySubcomponent = null
    }
}