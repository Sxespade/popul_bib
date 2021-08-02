package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}