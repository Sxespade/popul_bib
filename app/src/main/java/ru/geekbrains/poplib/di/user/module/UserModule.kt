package ru.geekbrains.poplib.di.user.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.di.user.UserScope
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.poplib.mvp.model.entity.room.Database
import ru.geekbrains.poplib.mvp.model.network.INetworkStatus
import ru.geekbrains.poplib.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.poplib.mvp.model.repo.retrofit.RetrofitGithubUsersRepo

@Module
open class UserModule {

    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }


    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus, cache)
    }
}