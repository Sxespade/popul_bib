package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.GithubUser

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}