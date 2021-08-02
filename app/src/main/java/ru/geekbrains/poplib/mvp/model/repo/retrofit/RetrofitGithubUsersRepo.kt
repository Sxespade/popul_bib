package ru.geekbrains.poplib.mvp.model.repo.retrofit

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.poplib.mvp.model.network.INetworkStatus
import ru.geekbrains.poplib.mvp.model.repo.IGithubUsersRepo

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubUsersCache) :
    IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cache.putUsers(users).toSingleDefault(users)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}