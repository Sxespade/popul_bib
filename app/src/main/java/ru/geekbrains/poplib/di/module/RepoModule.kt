package ru.geekbrains.poplib.di.module

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.poplib.mvp.model.network.INetworkStatus
import ru.geekbrains.poplib.mvp.model.repo.IGithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.model.repo.IGithubUsersRepo
import ru.geekbrains.poplib.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache): IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubRepositoriesCache): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

}