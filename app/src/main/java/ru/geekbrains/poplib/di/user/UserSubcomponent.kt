package ru.geekbrains.poplib.di.user

import dagger.Subcomponent
import ru.geekbrains.poplib.di.repository.RepositorySubcomponent
import ru.geekbrains.poplib.di.user.module.UserModule
import ru.geekbrains.poplib.mvp.presenter.UsersPresenter
import ru.geekbrains.poplib.ui.adapter.UsersRVAdapter

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}