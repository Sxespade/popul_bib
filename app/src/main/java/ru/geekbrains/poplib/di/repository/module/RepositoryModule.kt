package ru.geekbrains.poplib.di.repository.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.di.repository.RepositoryScope
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.entity.room.Database
import ru.geekbrains.poplib.mvp.model.network.INetworkStatus
import ru.geekbrains.poplib.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo

@Module
open class RepositoryModule {

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo {
        return RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
    }
}